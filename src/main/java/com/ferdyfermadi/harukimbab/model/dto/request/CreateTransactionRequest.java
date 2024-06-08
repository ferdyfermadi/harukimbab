package com.ferdyfermadi.harukimbab.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTransactionRequest {
    
    @NotBlank(message = "Customer ID is required")
    private String customerId;

    private List<TransactionDetailRequest> transactionDetails;

}
