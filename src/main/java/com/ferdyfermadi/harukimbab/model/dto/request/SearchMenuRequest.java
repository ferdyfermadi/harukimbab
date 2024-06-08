package com.ferdyfermadi.harukimbab.model.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchMenuRequest {
    private String name;
    private Long price;
}
