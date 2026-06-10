package com.aivle12.book_backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class BookUpdateRequestDto {     // 새로 만드는 파일!

    // @NotBlank 없음! 전부 선택사항
    private String title;
    private String author;
    private String content;
    private String coverImageUrl;
    private String genre;
    private String publisher;
    private Integer price;
    private Integer pages;
    private LocalDate pubDate;
}