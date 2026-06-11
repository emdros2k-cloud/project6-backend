package com.aivle12.book_backend.exception;

public class FollowNotFoundException extends RuntimeException {
    public FollowNotFoundException(Long followerId, Long followingId) {
        super("팔로우가 존재하지 않습니다. followerId=" + followerId + ", followingId=" + followingId);
    }
}
