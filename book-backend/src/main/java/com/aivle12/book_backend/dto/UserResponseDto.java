package com.aivle12.book_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String email;
    private String nickname;
    private String bio;
    private String profileImageUrl;
    private LocalDateTime createdAt;
}