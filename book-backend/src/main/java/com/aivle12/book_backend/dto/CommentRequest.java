package com.aivle12.book_backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    @NotBlank(message = "댓글 내용은 필수입니다")
    private String content;

    @NotNull(message = "별점은 필수입니다")
    @Min(value = 1, message = "별점은 최소 1점입니다")
    @Max(value = 5, message = "별점은 최대 5점입니다")
    private Integer rating;
}
