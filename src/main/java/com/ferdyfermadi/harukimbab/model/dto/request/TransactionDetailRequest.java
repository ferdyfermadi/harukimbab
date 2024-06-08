package com.ferdyfermadi.harukimbab.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetailRequest {

    @NotBlank(message = "Menu Id is required")
    private String menuId;

    @NotNull(message = "Total Price is required")
    @Min(value = 0, message = "stock must be greater than or equal 0")
    private Integer qty;

}
