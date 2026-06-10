package com.aivle12.book_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookResponseDto {

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
    private LocalDate pubDate;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}