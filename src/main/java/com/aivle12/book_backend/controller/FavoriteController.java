package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.dto.FavoriteResponse;
import com.aivle12.book_backend.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{bookId}/favorites")
    public ResponseEntity<FavoriteResponse> addFavorite(@PathVariable Long bookId,
                                                        @AuthenticationPrincipal Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favoriteService.addFavorite(bookId, userId));
    }

    @DeleteMapping("/{bookId}/favorites")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long bookId,
                                               @AuthenticationPrincipal Long userId) {
        favoriteService.removeFavorite(bookId, userId);
        return ResponseEntity.noContent().build();
    }
}
