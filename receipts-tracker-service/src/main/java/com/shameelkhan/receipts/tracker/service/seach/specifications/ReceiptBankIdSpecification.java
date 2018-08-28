package com.shameelkhan.receipts.tracker.service.seach.specifications;

import com.shameelkhan.receipts.tracker.service.dao.entities.Receipt;
import com.shameelkhan.receipts.tracker.service.seach.SearchCriteria;
import com.shameelkhan.receipts.tracker.service.seach.constants.SearchEntity;
import com.shameelkhan.receipts.tracker.service.seach.constants.SearchKey;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ReceiptBankIdSpecification implements CustomSpecification {

    public Specification<Receipt> buildWithCriteria(SearchCriteria searchCriteria) {

        return new Specification<Receipt>() {
            @Override
            public Predicate toPredicate(Root<Receipt> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.join(SearchEntity.BANK).get(SearchKey.BANK_ID), searchCriteria.getValue());
            }
        };
    }
}
