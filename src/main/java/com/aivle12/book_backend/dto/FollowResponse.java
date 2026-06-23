package com.aivle12.book_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FollowResponse {

    private Long id;
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;
}
