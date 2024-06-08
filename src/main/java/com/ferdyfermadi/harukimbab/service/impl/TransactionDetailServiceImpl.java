package com.ferdyfermadi.harukimbab.service.impl;

import com.ferdyfermadi.harukimbab.model.entity.TransactionDetail;
import com.ferdyfermadi.harukimbab.repository.TransactionDetailRepository;
import com.ferdyfermadi.harukimbab.service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {

    private final TransactionDetailRepository transactionDetailRepository;

    @Override
    public List<TransactionDetail> createBulk(List<TransactionDetail> transactionDetails) {
        return transactionDetailRepository.saveAllAndFlush(transactionDetails);
    }
}
