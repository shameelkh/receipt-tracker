package com.shameelkhan.receipts.tracker.ws.controllers;

import com.shameelkhan.receipts.tracker.service.dao.entities.*;
import com.shameelkhan.receipts.tracker.service.dao.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@RestController
public class PopulateController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    public PopulateController() {

    }

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public ResponseEntity populateDb() {
        User user1 = new User(null, "Tom", "Smith");
        User user2 = new User(null, "Jack", "Lee");

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);

        Bank bank1 = new Bank(null, "Capital One");
        Bank bank2 = new Bank(null, "Scotiabank");

        bank1 = bankRepository.save(bank1);
        bank2 = bankRepository.save(bank2);

        Merchant merchant1 = new Merchant(null, "Sam's Convenience Store", new HashSet());
        MerchantAddress merchantAddress1 = new MerchantAddress(null, merchant1, "2222", "Jane St", "Toronto");
        MerchantAddress merchantAddress2 = new MerchantAddress(null, merchant1, "4121", "Thomas Drive", "Toronto");
        merchant1.getAddresses().addAll(Arrays.asList(merchantAddress1, merchantAddress2));

        merchantRepository.save(merchant1);

        Receipt receipt1 = new Receipt();
        ReceiptLineItem lineItem1 = new ReceiptLineItem(null, "Bubble Gum", BigDecimal.valueOf(3.00));
        ReceiptLineItem lineItem2 = new ReceiptLineItem(null, "Hershey's Chocolate", BigDecimal.valueOf(2.75));
        ReceiptLineItem lineItem3 = new ReceiptLineItem(null, "Coke", BigDecimal.valueOf(1.25));

        receipt1.setUser(user1);
        receipt1.setBank(bank1);
        receipt1.setMerchant(merchant1);
        receipt1.setMerchantAddress(merchantAddress1);
        receipt1.setLineItems(Arrays.asList(lineItem1, lineItem2, lineItem3));
        receipt1.setTotal(BigDecimal.valueOf(7.00));
        receipt1.setDate(LocalDate.of(2016, 3, 10));

        receiptRepository.saveAndFlush(receipt1);


        return new ResponseEntity(HttpStatus.OK);
    }
}
