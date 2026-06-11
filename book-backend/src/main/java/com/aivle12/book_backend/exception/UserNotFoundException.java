package com.aivle12.book_backend.exception;

public class UserNotFoundException
        extends RuntimeException {
    public UserNotFoundException(Long id) {

        super("User "+id+" not exist");
    }
}
