package com.aivle12.book_backend.exception;

public class FavoriteAlreadyExistsException extends RuntimeException {

    public FavoriteAlreadyExistsException(Long userId, Long bookId) {
        super("이미 즐겨찾기에 등록된 도서입니다. userId=" + userId + ", bookId=" + bookId);
    }
}
