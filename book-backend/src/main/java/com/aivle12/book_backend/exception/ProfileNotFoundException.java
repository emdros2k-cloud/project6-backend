package com.aivle12.book_backend.exception;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(Long id) {

        super("User "+id+" not exist" );
    }
}
