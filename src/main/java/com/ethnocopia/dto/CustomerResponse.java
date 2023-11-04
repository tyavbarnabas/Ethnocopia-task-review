package com.ethnocopia.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerResponse {

    private String responseCode;
    private String message;
    private Object details;
}
