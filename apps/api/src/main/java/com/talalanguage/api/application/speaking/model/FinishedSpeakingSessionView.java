package com.talalanguage.api.application.speaking.model;

public record FinishedSpeakingSessionView(
        String sessionId,
        String status,
        SpeakingSummaryView summary
) {
}
