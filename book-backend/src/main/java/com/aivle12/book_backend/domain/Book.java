package com.aivle12.book_backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_id")
    private Long authorId;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    @Lob
    private String content;

    private String coverImageUrl;

    @NotBlank
    private String genre;

    private String publisher;

    private Integer price;

    private Integer pages;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer viewCount;

    private LocalDate pubDate;
}
