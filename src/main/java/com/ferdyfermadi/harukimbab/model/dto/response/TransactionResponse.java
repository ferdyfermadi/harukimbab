package com.ferdyfermadi.harukimbab.model.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private String id;
    private String customerId;
    private Date transactionDate;
    private List<TransactionDetailResponse> transactionDetail;

}
