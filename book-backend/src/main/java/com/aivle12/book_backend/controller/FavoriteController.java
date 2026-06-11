package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.dto.FavoriteRequest;
import com.aivle12.book_backend.dto.FavoriteResponse;
import com.aivle12.book_backend.service.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{bookId}/favorites")
    public ResponseEntity<FavoriteResponse> addFavorite(@PathVariable Long bookId,
                                                        @Valid @RequestBody FavoriteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favoriteService.addFavorite(bookId, request));
    }

    @DeleteMapping("/{bookId}/favorites")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long bookId,
                                               @Valid @RequestBody FavoriteRequest request) {
        favoriteService.removeFavorite(bookId, request);
        return ResponseEntity.noContent().build();
    }
}
