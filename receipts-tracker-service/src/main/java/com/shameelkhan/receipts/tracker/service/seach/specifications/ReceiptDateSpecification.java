package com.shameelkhan.receipts.tracker.service.seach.specifications;

import com.shameelkhan.receipts.tracker.service.dao.entities.Receipt;
import com.shameelkhan.receipts.tracker.service.seach.SearchCriteria;
import com.shameelkhan.receipts.tracker.service.seach.constants.SearchOperator;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class ReceiptDateSpecification implements CustomSpecification<Receipt> {

    @Override
    public Specification<Receipt> buildWithCriteria(SearchCriteria searchCriteria) {
        return new Specification<Receipt>() {

            @Override
            public Predicate toPredicate(Root<Receipt> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                LocalDate date = LocalDate.parse(searchCriteria.getValue().toString());

                if (searchCriteria.getOperation().equalsIgnoreCase(SearchOperator.GREATER_THAN)) {
                    return criteriaBuilder.greaterThan(
                            root.get(searchCriteria.getKey()), date);
                }
                else if (searchCriteria.getOperation().equalsIgnoreCase(SearchOperator.LESS_THAN)) {
                    return criteriaBuilder.lessThan(
                            root.get(searchCriteria.getKey()), date);
                }
                else if (searchCriteria.getOperation().equalsIgnoreCase(SearchOperator.EQUAL)) {
                    return criteriaBuilder.equal(root.get(searchCriteria.getKey()), date);
                }

                return null;
            }
        };
    }
}
