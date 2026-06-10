package com.aivle12.book_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class BookRequestDto {

    @NotBlank(message = "제목은 필수입니다")
    private String title;

    @NotBlank(message = "작가는 필수입니다")
    private String author;

    @NotBlank(message = "내용은 필수입니다")
    private String content;

    private String coverImageUrl;
    private String genre;
    private String publisher;
    private Integer price;
    private Integer pages;
    private LocalDate pubDate;
}