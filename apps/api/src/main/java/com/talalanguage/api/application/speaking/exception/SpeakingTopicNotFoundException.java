package com.talalanguage.api.application.speaking.exception;

public class SpeakingTopicNotFoundException extends RuntimeException {

    public SpeakingTopicNotFoundException(String topicId) {
        super("Speaking topic not found: " + topicId);
    }
}
