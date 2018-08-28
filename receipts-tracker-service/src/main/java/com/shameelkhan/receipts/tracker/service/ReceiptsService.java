package com.shameelkhan.receipts.tracker.service;

import com.shameelkhan.receipts.tracker.domain.ReceiptDto;
import com.shameelkhan.receipts.tracker.service.dao.entities.Receipt;
import com.shameelkhan.receipts.tracker.service.dao.entities.ReceiptLineItem;
import com.shameelkhan.receipts.tracker.service.dao.entities.User;
import com.shameelkhan.receipts.tracker.service.dao.repositories.ReceiptRepository;
import com.shameelkhan.receipts.tracker.service.dao.repositories.UserRepository;
import com.shameelkhan.receipts.tracker.service.mappers.ReceiptMapper;
import com.shameelkhan.receipts.tracker.service.seach.constants.SearchKey;
import com.shameelkhan.receipts.tracker.service.seach.SearchCriteria;
import com.shameelkhan.receipts.tracker.service.seach.constants.SearchOperator;
import com.shameelkhan.receipts.tracker.service.seach.specifications.SpecificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class ReceiptsService {

    private ReceiptRepository receiptRepository;
    private UserRepository userRepository;
    private ReceiptMapper receiptMapper;
    private SpecificationFactory specificationFactory;

    @Autowired
    public ReceiptsService(ReceiptRepository receiptRepository, UserRepository userRepository,
                           ReceiptMapper receiptMapper, SpecificationFactory specificationFactory) {
        this.receiptRepository = receiptRepository;
        this.userRepository = userRepository;
        this.receiptMapper = receiptMapper;
        this.specificationFactory = specificationFactory;
    }

    public List<ReceiptDto> getReceiptsForUser(Long userId) {
        User user = userRepository.findOne(userId);

        List<Receipt> dbReceipts = receiptRepository.findByUser(user);

        List<ReceiptDto> receipts = new ArrayList<ReceiptDto>();

        for(Receipt receipt : dbReceipts) {
            receipts.add(receiptMapper.mapDbToDomain(receipt));
        }

        return receipts;
    }

    public List<ReceiptDto> searchReceiptsForUser(Long userId, List<SearchCriteria> searchCriteria) {

        SearchCriteria userIdSearchCriteria = new SearchCriteria(SearchKey.USER_ID, SearchOperator.EQUAL, userId);
        Specification specifications =
                specificationFactory.getSpecification(userIdSearchCriteria.getKey()).buildWithCriteria(userIdSearchCriteria);

        for(SearchCriteria criteria : searchCriteria) {
            Specification newSpecification =
                    specificationFactory.getSpecification(criteria.getKey()).buildWithCriteria(criteria);

            specifications = Specifications.where(specifications).and(newSpecification);
        }

        List<Receipt> receipts = receiptRepository.findAll(specifications);
        return receiptMapper.mapDbToDomain(receipts);
    }

    public ReceiptDto saveReceipt(ReceiptDto receiptDto) {
        Receipt receipt = receiptMapper.mapDomainToDb(receiptDto);

        BigDecimal receiptTotal = calculateReceiptTotal(receipt);
        receipt.setTotal(receiptTotal);

        if(receipt.getDate() == null) {
            receipt.setDate(LocalDate.now());
        }

        receipt = receiptRepository.save(receipt);
        return receiptMapper.mapDbToDomain(receipt);
    }

    public void patchReceiptDate(Long receiptId, LocalDate newDate) {
        Receipt receiptToPatch = receiptRepository.findOne(receiptId);
        receiptToPatch.setDate(newDate);

        receiptRepository.save(receiptToPatch);
    }

    public void batchDeleteReceipts(List<Long> receiptsIds) {
        for (Long receiptId : receiptsIds) {
            receiptRepository.delete(receiptId);
        }
    }

    private BigDecimal calculateReceiptTotal(Receipt receipt) {
        BigDecimal total = BigDecimal.ZERO;

        for (ReceiptLineItem lineItem : receipt.getLineItems()) {
            total = total.add(lineItem.getPrice());
        }

        return total;
    }
}
