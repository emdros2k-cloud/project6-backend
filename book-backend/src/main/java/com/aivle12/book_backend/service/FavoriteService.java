package com.aivle12.book_backend.service;

import com.aivle12.book_backend.dto.FavoriteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    public FavoriteResponseDto addFavorite(Long bookId) {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public void removeFavorite(Long bookId) {
        // 백엔드 2 팀원이 채울 예정
    }
}