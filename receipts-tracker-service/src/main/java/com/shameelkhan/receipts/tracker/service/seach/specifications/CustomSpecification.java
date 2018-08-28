package com.shameelkhan.receipts.tracker.service.seach.specifications;

import com.shameelkhan.receipts.tracker.service.seach.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

public interface CustomSpecification<T> {

    Specification<T> buildWithCriteria(SearchCriteria searchCriteria);
}
