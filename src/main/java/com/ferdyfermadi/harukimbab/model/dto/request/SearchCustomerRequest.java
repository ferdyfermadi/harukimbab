package com.ferdyfermadi.harukimbab.model.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCustomerRequest {
    private String name;
    private String address;
    private String phone;
}
