package com.shameelkhan.receipts.tracker.service.seach.specifications;

import com.shameelkhan.receipts.tracker.service.seach.constants.SearchKey;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SpecificationFactory {

    private Map<String, CustomSpecification> searchKeyToSpecificationMap;
    private CustomSpecification generalSpecification;

    public SpecificationFactory() {
        generalSpecification = new GeneralSpecification();

        searchKeyToSpecificationMap = new HashMap<>();
        searchKeyToSpecificationMap.put(SearchKey.USER_ID, new ReceiptUserIdSpecification());
        searchKeyToSpecificationMap.put(SearchKey.BANK_ID, new ReceiptBankIdSpecification());
        searchKeyToSpecificationMap.put(SearchKey.DATE, new ReceiptDateSpecification());
    }

    public CustomSpecification getSpecification(String searchKey) {
        CustomSpecification customSpecification;

        if(searchKeyToSpecificationMap.containsKey(searchKey)) {
            customSpecification = searchKeyToSpecificationMap.get(searchKey);
        }
        else {
            customSpecification = generalSpecification;
        }

        return customSpecification;
    }
}
