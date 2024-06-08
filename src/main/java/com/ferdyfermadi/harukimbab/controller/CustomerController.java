package com.ferdyfermadi.harukimbab.controller;

import com.ferdyfermadi.harukimbab.model.constant.APIUrl;
import com.ferdyfermadi.harukimbab.model.constant.ResponseMessage;
import com.ferdyfermadi.harukimbab.model.dto.request.CreateCustomerRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.PagingRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.SearchCustomerRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.UpdateCustomerRequest;
import com.ferdyfermadi.harukimbab.model.dto.response.CommonResponse;
import com.ferdyfermadi.harukimbab.model.dto.response.PagingResponse;
import com.ferdyfermadi.harukimbab.model.entity.Customer;
import com.ferdyfermadi.harukimbab.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse<Customer>> createNewCustomer(@RequestBody CreateCustomerRequest customerRequest) {
        Customer newCustomer = customerService.create(customerRequest);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newCustomer)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Customer>> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getById(id);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(customer)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Customer>>> getAllCustomer(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ){
        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();

        SearchCustomerRequest customerRequest = SearchCustomerRequest.builder().build();
        Page<Customer> allCustomer = customerService.getAll(pagingRequest, customerRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allCustomer.getTotalPages())
                .totalElements(allCustomer.getTotalElements())
                .page(allCustomer.getPageable().getPageNumber() + 1)
                .size(allCustomer.getPageable().getPageSize())
                .hasNext(allCustomer.hasNext())
                .hasPrevious(allCustomer.hasPrevious())
                .build();

        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(allCustomer.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(@RequestBody UpdateCustomerRequest customerRequest){

        Customer customer = customerService.update(customerRequest);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(customer)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Customer>> deletedCustomerById(@PathVariable String id){
        customerService.deletedById(id);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA + id)
                .build();

        return ResponseEntity.ok(response);
    }
}
