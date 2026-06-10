package com.aivle12.book_backend.dto;

import com.aivle12.book_backend.domain.Favorite;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FavoriteResponse {

    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDateTime createdAt;

    public static FavoriteResponse from(Favorite favorite) {
        return FavoriteResponse.builder()
                .id(favorite.getId())
                .userId(favorite.getUserId())
                .bookId(favorite.getBookId())
                .createdAt(favorite.getCreatedAt())
                .build();
    }
}
