package com.ferdyfermadi.harukimbab.service;

import com.ferdyfermadi.harukimbab.model.dto.request.CreateTransactionRequest;
import com.ferdyfermadi.harukimbab.model.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse create(CreateTransactionRequest transactionRequest);
    List<TransactionResponse> getAll();
}
