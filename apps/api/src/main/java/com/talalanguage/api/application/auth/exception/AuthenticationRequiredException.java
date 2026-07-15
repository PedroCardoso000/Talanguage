package com.talalanguage.api.application.auth.exception;

public class AuthenticationRequiredException extends RuntimeException {

    public AuthenticationRequiredException() {
        super("Authentication is required.");
    }
}
