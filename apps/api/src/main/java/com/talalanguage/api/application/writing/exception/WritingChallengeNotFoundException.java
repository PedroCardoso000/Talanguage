package com.talalanguage.api.application.writing.exception;

public class WritingChallengeNotFoundException extends RuntimeException {

    public WritingChallengeNotFoundException() {
        super("Writing challenge not found.");
    }
}
