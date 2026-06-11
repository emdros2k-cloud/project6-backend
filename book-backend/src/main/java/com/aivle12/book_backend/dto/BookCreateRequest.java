package com.aivle12.book_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookCreateRequest {

    @NotNull
    private Long authorId;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String content;
    private String coverImageUrl;
    private String genre;
    private String publisher;

    @Positive
    private Integer price;

    @Positive
    private Integer pages;

    private String isbn;
    private LocalDate pubDate;
}
