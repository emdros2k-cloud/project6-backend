package com.aivle12.book_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FavoriteResponseDto {

    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDateTime createdAt;
}