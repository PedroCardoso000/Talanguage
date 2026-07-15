package com.talalanguage.api.application.auth.exception;

public class EmailAlreadyInUseException extends RuntimeException {

    public EmailAlreadyInUseException() {
        super("This email is already in use.");
    }
}
