package com.talalanguage.api.application.auth.exception;

public class WeakPasswordException extends RuntimeException {

    public WeakPasswordException() {
        super("Password must have at least 8 characters, including letters and numbers.");
    }
}
