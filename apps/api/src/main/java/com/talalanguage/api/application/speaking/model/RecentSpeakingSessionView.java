package com.talalanguage.api.application.speaking.model;

public record RecentSpeakingSessionView(
        String sessionId,
        String topicId,
        String topicTitle,
        String startedAt,
        String finishedAt,
        int totalMessages,
        long approximateDurationMinutes,
        String feedback,
        String nextAction
) {
}
