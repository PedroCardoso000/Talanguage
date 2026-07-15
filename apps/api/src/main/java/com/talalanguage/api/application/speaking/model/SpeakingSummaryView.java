package com.talalanguage.api.application.speaking.model;

public record SpeakingSummaryView(
        int totalMessages,
        long approximateDurationMinutes,
        String topicTitle,
        String feedback,
        String nextAction
) {
}
