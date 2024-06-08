package com.ferdyfermadi.harukimbab.specification;

import com.ferdyfermadi.harukimbab.model.dto.request.SearchCustomerRequest;
import com.ferdyfermadi.harukimbab.model.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(SearchCustomerRequest request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(request.getName() != null){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + request.getName().toLowerCase() + "%"
                        )
                );
            }
            if(request.getAddress() != null){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("address")),
                                "%" + request.getAddress().toLowerCase() + "%"
                        )
                );
            }
            if(request.getPhone() != null){
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("phone"),
                                Integer.parseInt(request.getPhone())
                        )
                );
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}

