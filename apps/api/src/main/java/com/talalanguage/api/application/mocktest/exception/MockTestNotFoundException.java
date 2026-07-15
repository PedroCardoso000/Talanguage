package com.talalanguage.api.application.mocktest.exception;

public class MockTestNotFoundException extends RuntimeException {

    public MockTestNotFoundException() {
        super("Mock test not found.");
    }
}
