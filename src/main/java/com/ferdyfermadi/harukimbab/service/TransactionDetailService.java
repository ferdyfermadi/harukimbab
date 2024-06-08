package com.ferdyfermadi.harukimbab.service;

import com.ferdyfermadi.harukimbab.model.entity.TransactionDetail;

import java.util.List;

public interface TransactionDetailService {
    List<TransactionDetail> createBulk (List<TransactionDetail> transactionDetails);

}
