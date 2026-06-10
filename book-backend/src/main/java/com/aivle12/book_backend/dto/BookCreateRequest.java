package com.aivle12.book_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookCreateRequest {

    private Long authorId;
    private String title;
    private String author;
    private String content;
    private String coverImageUrl;
    private String genre;
    private String publisher;
    private Integer price;
    private Integer pages;
    private String isbn;
    private LocalDate pubDate;
}
