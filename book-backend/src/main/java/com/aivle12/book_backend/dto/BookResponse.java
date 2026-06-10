package com.aivle12.book_backend.dto;

import com.aivle12.book_backend.domain.Book;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class BookResponse {

    private Long id;
    private Long authorId;
    private String title;
    private String author;
    private String content;
    private String coverImageUrl;
    private String genre;
    private String publisher;
    private Integer price;
    private Integer pages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer viewCount;
    private String isbn;

    private LocalDate pubDate;

    public static BookResponse from(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .authorId(book.getAuthorId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .content(book.getContent())
                .coverImageUrl(book.getCoverImageUrl())
                .genre(book.getGenre())
                .publisher(book.getPublisher())
                .price(book.getPrice())
                .pages(book.getPages())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .viewCount(book.getViewCount())
                .isbn(book.getIsbn())
                .pubDate(book.getPubDate())
                .build();
    }
}
