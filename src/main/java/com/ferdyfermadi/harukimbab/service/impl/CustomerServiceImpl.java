package com.ferdyfermadi.harukimbab.service.impl;

import com.ferdyfermadi.harukimbab.model.dto.request.CreateCustomerRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.PagingRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.SearchCustomerRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.UpdateCustomerRequest;
import com.ferdyfermadi.harukimbab.model.entity.Customer;
import com.ferdyfermadi.harukimbab.repository.CustomerRepository;
import com.ferdyfermadi.harukimbab.service.CustomerService;
import com.ferdyfermadi.harukimbab.specification.CustomerSpecification;
import com.ferdyfermadi.harukimbab.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;

    @Override
    public Customer create(CreateCustomerRequest customerRequest) {
        validationUtil.validate(customerRequest);
        Customer newCustomer = Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .phone(customerRequest.getPhone())
                .build();
        return customerRepository.saveAndFlush(newCustomer);
    }

    @Override
    public Customer getById(String id) {
        Optional<Customer> customerById = customerRepository.findById(id);
        if (customerById.isEmpty()) {
            throw new RuntimeException("Customer Not Found");
        }
        return customerById.get();
    }

    @Override
    public Page<Customer> getAll(PagingRequest pagingRequest, SearchCustomerRequest customerRequest) {
        validationUtil.validate(pagingRequest);

        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }

        String validSortBy;
        if ("name".equalsIgnoreCase(pagingRequest.getSortBy()) || "address".equalsIgnoreCase(pagingRequest.getSortBy()) || "phone".equalsIgnoreCase(pagingRequest.getSortBy())) {
            validSortBy = pagingRequest.getSortBy();
        } else {
            validSortBy = "name";
        }

        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), validSortBy);

        Pageable pageable = PageRequest.of((pagingRequest.getPage() - 1), pagingRequest.getSize(), sort);

        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(customerRequest);
        return customerRepository.findAll(customerSpecification, pageable);
    }

    @Override
    public Customer update(UpdateCustomerRequest customerRequest) {
        validationUtil.validate(customerRequest);
        Customer customer = Customer.builder()
                .id(customerRequest.getId())
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .phone(customerRequest.getPhone())
                .build();
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deletedById(String id) {
        Customer customer = getById(id);
        customerRepository.delete(customer);
    }
}
