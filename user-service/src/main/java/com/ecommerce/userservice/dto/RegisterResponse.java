package com.ecommerce.userservice.dto;

import lombok.Builder;

@Builder
public record RegisterResponse(String username, String email) {
}
