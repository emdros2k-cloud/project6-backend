package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.dto.*;
import com.aivle12.book_backend.service.AiCoverService;
import com.aivle12.book_backend.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AiCoverService aiCoverService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks(
            @RequestParam(required = false) Long authorId) {
        return ResponseEntity.ok(bookService.getBooks(authorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookCreateRequest request,
                                                   @AuthenticationPrincipal Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(request, userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id,
                                                   @Valid @RequestBody BookUpdateRequest request,
                                                   @AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(bookService.updateBook(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id,
                                           @AuthenticationPrincipal Long userId) {
        bookService.deleteBook(id, userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/views")
    public ResponseEntity<BookResponse> incrementViews(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.incrementViewCount(id));
    }

    @PatchMapping("/{id}/cover")
    public ResponseEntity<BookResponse> saveCover(@PathVariable Long id,
                                                  @Valid @RequestBody BookCoverRequest request,
                                                  @AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(bookService.updateCover(id, request));
    }

    @PatchMapping("/{id}/cover-editor")
    public ResponseEntity<BookResponse> updateCoverFromEditor(@PathVariable Long id,
                                                              @Valid @RequestBody BookCoverRequest request,
                                                              @AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(bookService.updateCover(id, request));
    }

    @PostMapping("/cover/generate")
    public ResponseEntity<BookCoverGenerateResponse> generateCover(@Valid @RequestBody BookCoverGenerateRequest request,
                                                                   @AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(aiCoverService.generateCovers(request));
    }

    @PostMapping("/{id}/cover/generate")
    public ResponseEntity<BookCoverGenerateResponse> generateCoverForBook(@PathVariable Long id,
                                                                          @Valid @RequestBody BookCoverGenerateRequest request,
                                                                          @AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(aiCoverService.generateCoversForBook(id, request));
    }
}
