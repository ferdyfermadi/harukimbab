package com.ferdyfermadi.harukimbab.model.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetailResponse {
    private String id;
    private String customerId;
    private String menuId;
    private Long menuPrice;
    private Long totalPrice;
    private Integer qty;
}
