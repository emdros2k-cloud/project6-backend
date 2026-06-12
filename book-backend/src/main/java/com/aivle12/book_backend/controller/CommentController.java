package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.dto.CommentRequest;
import com.aivle12.book_backend.dto.CommentResponse;
import com.aivle12.book_backend.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // GET /books/{bookId}/comments
    @GetMapping("/books/{bookId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long bookId) {
        return ResponseEntity.ok(commentService.getComments(bookId));
    }

    // POST /books/{bookId}/comments
    @PostMapping("/books/{bookId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long bookId,
            @RequestBody @Valid CommentRequest dto,
            @AuthenticationPrincipal Long userId) {
        return ResponseEntity.status(201).body(commentService.createComment(bookId, dto, userId));
    }

    // PATCH /comments/{id}
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long id,
            @RequestBody @Valid CommentRequest dto,
            @AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(commentService.updateComment(id, dto, userId));
    }

    // DELETE /comments/{id}
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long id,
            @AuthenticationPrincipal Long userId) {
        commentService.deleteComment(id, userId);
        return ResponseEntity.noContent().build();
    }
}