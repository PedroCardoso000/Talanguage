package com.talalanguage.api.web.speaking.dto;

import com.talalanguage.api.application.speaking.model.RecentSpeakingSessionView;

public record RecentSpeakingSessionResponseDto(
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
    public static RecentSpeakingSessionResponseDto from(RecentSpeakingSessionView view) {
        return new RecentSpeakingSessionResponseDto(
                view.sessionId(),
                view.topicId(),
                view.topicTitle(),
                view.startedAt(),
                view.finishedAt(),
                view.totalMessages(),
                view.approximateDurationMinutes(),
                view.feedback(),
                view.nextAction()
        );
    }
}
