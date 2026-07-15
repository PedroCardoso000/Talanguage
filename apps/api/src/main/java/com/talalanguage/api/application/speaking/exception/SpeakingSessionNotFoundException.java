package com.talalanguage.api.application.speaking.exception;

public class SpeakingSessionNotFoundException extends RuntimeException {

    public SpeakingSessionNotFoundException(String sessionId) {
        super("Speaking session not found: " + sessionId);
    }
}
