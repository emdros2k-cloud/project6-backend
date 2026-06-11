package com.aivle12.book_backend.exception;

public class FavoriteNotFoundException extends RuntimeException {

    public FavoriteNotFoundException(Long userId, Long bookId) {
        super("즐겨찾기가 존재하지 않습니다. userId=" + userId + ", bookId=" + bookId);
    }
}
