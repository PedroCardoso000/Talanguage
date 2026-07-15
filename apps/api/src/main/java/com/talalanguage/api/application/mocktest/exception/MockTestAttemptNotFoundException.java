package com.talalanguage.api.application.mocktest.exception;

public class MockTestAttemptNotFoundException extends RuntimeException {

    public MockTestAttemptNotFoundException() {
        super("Mock test attempt not found.");
    }
}
