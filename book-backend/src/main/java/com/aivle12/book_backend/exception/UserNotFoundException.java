package com.aivle12.book_backend.exception;

public class UserNotFoundException
        extends RuntimeException {
    public UserNotFoundException(String email) {

        super("email "+email+" not exist");
    }
}
