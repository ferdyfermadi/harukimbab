package com.ferdyfermadi.harukimbab.controller;

import com.ferdyfermadi.harukimbab.model.constant.APIUrl;
import com.ferdyfermadi.harukimbab.model.dto.request.CreateTransactionRequest;
import com.ferdyfermadi.harukimbab.model.dto.response.TransactionResponse;
import com.ferdyfermadi.harukimbab.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANSACTION_API)
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponse createNewTransaction(
            @RequestBody CreateTransactionRequest request
    ){
        return transactionService.create(request);
    }

    @GetMapping
    public List<TransactionResponse> getAllTransaction(){
        return transactionService.getAll();
    }
}
