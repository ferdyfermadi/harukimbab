package com.ferdyfermadi.harukimbab.service;

import com.ferdyfermadi.harukimbab.model.dto.request.CreateCustomerRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.PagingRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.SearchCustomerRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.UpdateCustomerRequest;
import com.ferdyfermadi.harukimbab.model.entity.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Customer create(CreateCustomerRequest customerRequest);
    Customer getById(String id);
    Page<Customer> getAll(PagingRequest pagingRequest, SearchCustomerRequest customerRequest);
    Customer update(UpdateCustomerRequest customerRequest);
    void deletedById(String id);
}
