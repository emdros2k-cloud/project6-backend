package com.aivle12.book_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookCoverGenerateRequest {

    @NotBlank
    private String prompt;

    @NotBlank
    private String model;   // gpt-image-2, gpt-image-1, dall-e-3

    private String quality; // Low, Medium, High
}
