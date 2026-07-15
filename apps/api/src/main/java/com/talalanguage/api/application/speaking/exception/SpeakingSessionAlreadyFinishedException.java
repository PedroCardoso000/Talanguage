package com.talalanguage.api.application.speaking.exception;

public class SpeakingSessionAlreadyFinishedException extends RuntimeException {

    public SpeakingSessionAlreadyFinishedException(String sessionId) {
        super("Speaking session is already finished: " + sessionId);
    }
}
