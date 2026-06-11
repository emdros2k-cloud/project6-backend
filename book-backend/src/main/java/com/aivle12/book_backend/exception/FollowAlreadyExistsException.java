package com.aivle12.book_backend.exception;

public class FollowAlreadyExistsException extends RuntimeException {
    public FollowAlreadyExistsException(Long followerId, Long followingId) {
        super("이미 팔로우한 사용자입니다. followerId=" + followerId + ", followingId=" + followingId);
    }
}
