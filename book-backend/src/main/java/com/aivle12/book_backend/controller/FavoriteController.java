package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.dto.FavoriteResponseDto;
import com.aivle12.book_backend.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    // POST /books/{bookId}/favorites
    @PostMapping("/books/{bookId}/favorites")
    public ResponseEntity<FavoriteResponseDto> addFavorite(@PathVariable Long bookId) {
        return ResponseEntity.status(201).body(favoriteService.addFavorite(bookId));
    }

    // DELETE /books/{bookId}/favorites
    @DeleteMapping("/books/{bookId}/favorites")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long bookId) {
        favoriteService.removeFavorite(bookId);
        return ResponseEntity.noContent().build();
    }
}