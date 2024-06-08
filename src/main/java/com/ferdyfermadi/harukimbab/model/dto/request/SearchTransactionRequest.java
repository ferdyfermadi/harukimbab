package com.ferdyfermadi.harukimbab.model.dto.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchTransactionRequest {
    private String customerId;
    private Long totalPrice;
    private List<TransactionDetailRequest> transactionDetail;
}
