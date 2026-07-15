package com.talalanguage.api.application.speaking.model;

public record StartedSpeakingSessionView(
        String sessionId,
        String status,
        String startedAt,
        String prompt
) {
}
