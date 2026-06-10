package com.aivle12.book_backend.exception;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String email) {
        super("email "+email+" not exist" );
    }
}
