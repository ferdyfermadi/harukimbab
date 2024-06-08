package com.ferdyfermadi.harukimbab.service.impl;

import com.ferdyfermadi.harukimbab.model.dto.request.CreateTransactionRequest;
import com.ferdyfermadi.harukimbab.model.dto.request.TransactionDetailRequest;
import com.ferdyfermadi.harukimbab.model.dto.response.TransactionDetailResponse;
import com.ferdyfermadi.harukimbab.model.dto.response.TransactionResponse;
import com.ferdyfermadi.harukimbab.model.entity.Customer;
import com.ferdyfermadi.harukimbab.model.entity.Menu;
import com.ferdyfermadi.harukimbab.model.entity.Transaction;
import com.ferdyfermadi.harukimbab.model.entity.TransactionDetail;
import com.ferdyfermadi.harukimbab.repository.TransactionRepository;
import com.ferdyfermadi.harukimbab.service.CustomerService;
import com.ferdyfermadi.harukimbab.service.MenuService;
import com.ferdyfermadi.harukimbab.service.TransactionDetailService;
import com.ferdyfermadi.harukimbab.service.TransactionService;
import com.ferdyfermadi.harukimbab.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final MenuService menuService;
    private final TransactionDetailService transactionDetailService;
    private final ValidationUtil validationUtil;


    @Override
    public TransactionResponse create(CreateTransactionRequest transactionRequest) {
        validationUtil.validate(transactionRequest);
        Customer customer = customerService.getById(transactionRequest.getCustomerId());

        //menu.getPrice * trx_detail.getQty


        Transaction transaction = Transaction.builder()
                .customer(customer)
                .transactionDate(new Date())
                .build();
        transactionRepository.saveAndFlush(transaction);

        List<TransactionDetail> transactionDetail = transactionRequest.getTransactionDetails().stream().map(
                detailRequest -> {
                    Menu menu = menuService.getById(detailRequest.getMenuId());

                    return TransactionDetail.builder()
                            .menu(menu)
                            .transaction(transaction)
                            .qty(detailRequest.getQty())
                            .menuPrice(menu.getPrice())
                            .build();
                }).toList();

        transactionDetailService.createBulk(transactionDetail);

        transaction.setTransactionDetail(transactionDetail);

        List<TransactionDetailResponse> trxDetailResponse = transactionDetail.stream()
                .map(detail -> {

                    return TransactionDetailResponse.builder()
                            .id(detail.getId())
                            .menuId(detail.getMenu().getId())
                            .customerId(detail.getCustomer().getId())
                            .menuPrice(detail.getMenu().getPrice())
                            .totalPrice(detail.getQty() * detail.getMenu().getPrice())
                            .qty(detail.getQty())
                            .build();
                }).toList();


        return TransactionResponse.builder()
                .id(transaction.getId())
                .customerId(transaction.getCustomer().getId())
                .transactionDate(transaction.getTransactionDate())
                .transactionDetail(trxDetailResponse)
                .build();
    }

    @Override
    public List<TransactionResponse> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map(transaction -> {
            List<TransactionDetailResponse> trxDetailResponse = transaction.getTransactionDetail().stream().map(detail -> {
                return TransactionDetailResponse.builder()
                        .id(detail.getId())
                        .menuId(detail.getMenu().getId())
                        .menuPrice(detail.getMenu().getPrice())
                        .totalPrice(detail.getQty() * detail.getMenu().getPrice())
                        .qty(detail.getQty())
                        .build();
            }).toList();

            return TransactionResponse.builder()
                    .id(transaction.getId())
                    .customerId(transaction.getCustomer().getId())
                    .transactionDate(transaction.getTransactionDate())
                    .transactionDetail(trxDetailResponse)
                    .build();
        }).toList();
    }
}
