package com.aivle12.book_backend.dto;

public record LoginResponse(
        String accessToken,
        String tokenType,
        Long userId,
        String email,
        String nickname
) {
}
