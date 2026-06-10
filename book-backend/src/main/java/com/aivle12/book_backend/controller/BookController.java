package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.dto.BookRequestDto;
import com.aivle12.book_backend.dto.BookResponseDto;
import com.aivle12.book_backend.dto.BookUpdateRequestDto;   // ← 이 줄 추가!
import com.aivle12.book_backend.dto.CoverRequestDto;
import com.aivle12.book_backend.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // GET /books
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // GET /books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // POST /books
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody @Valid BookRequestDto dto) {
        BookResponseDto created = bookService.createBook(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    // PATCH /books/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long id,
            @RequestBody BookUpdateRequestDto dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH /books/{id}/views
    @PatchMapping("/{id}/views")
    public ResponseEntity<Void> increaseViewCount(@PathVariable Long id) {
        bookService.increaseViewCount(id);
        return ResponseEntity.ok().build();
    }

    // PATCH /books/{id}/cover (AI 표지 저장)
    @PatchMapping("/{id}/cover")
    public ResponseEntity<BookResponseDto> saveCover(
            @PathVariable Long id,
            @RequestBody @Valid CoverRequestDto dto) {
        return ResponseEntity.ok(bookService.updateCover(id, dto));
    }

    // PATCH /books/{id}/cover-editor (AI 표지 수정)
    @PatchMapping("/{id}/cover-editor")
    public ResponseEntity<BookResponseDto> updateCover(
            @PathVariable Long id,
            @RequestBody @Valid CoverRequestDto dto) {
        return ResponseEntity.ok(bookService.updateCover(id, dto));
    }
}