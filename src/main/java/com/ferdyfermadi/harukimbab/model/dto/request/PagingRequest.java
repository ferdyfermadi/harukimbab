package com.ferdyfermadi.harukimbab.model.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingRequest {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
