package com.shameelkhan.receipts.tracker.service.dao;

import com.shameelkhan.receipts.tracker.service.dao.entities.Bank;
import com.shameelkhan.receipts.tracker.service.dao.entities.Receipt;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Receipt.class)
public class Bank_ {

    public static volatile SingularAttribute<Bank, Long> bankId;

}
