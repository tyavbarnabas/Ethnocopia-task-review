package com.ethnocopia.dto;

public record CustomerRequest(
        String name,
        String email,
        int age
) {
}
