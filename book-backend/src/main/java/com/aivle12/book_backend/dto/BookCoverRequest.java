package com.aivle12.book_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookCoverRequest {

    @NotBlank
    private String coverImageUrl;
}
