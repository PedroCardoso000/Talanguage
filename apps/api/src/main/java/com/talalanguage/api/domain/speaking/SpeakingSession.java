package com.talalanguage.api.domain.speaking;

import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record SpeakingSession(
        String id,
        UserId userId,
        SpeakingLanguage language,
        SpeakingLevel level,
        String topicId,
        SpeakingSessionStatus status,
        String currentPrompt,
        List<String> responses,
        int responseCount,
        int totalWordCount,
        int totalSentenceCount,
        Instant startedAt,
        Instant finishedAt,
        SpeakingFeedback feedbackSummary
) {
    public static SpeakingSession start(
            UserId userId,
            SpeakingLanguage language,
            SpeakingLevel level,
            SpeakingTopic topic
    ) {
        return new SpeakingSession(
                UUID.randomUUID().toString(),
                userId,
                language,
                level,
                topic.id(),
                SpeakingSessionStatus.IN_PROGRESS,
                topic.initialPrompt(),
                List.of(),
                0,
                0,
                0,
                Instant.now(),
                null,
                null
        );
    }

    public SpeakingSession submitResponse(String content, String nextPrompt) {
        ArrayList<String> updatedResponses = new ArrayList<>(responses);
        updatedResponses.add(content);
        int words = countWords(content);
        int sentences = countSentences(content);

        return new SpeakingSession(
                id,
                userId,
                language,
                level,
                topicId,
                status,
                nextPrompt,
                List.copyOf(updatedResponses),
                responseCount + 1,
                totalWordCount + words,
                totalSentenceCount + sentences,
                startedAt,
                finishedAt,
                feedbackSummary
        );
    }

    public SpeakingSession finish(SpeakingFeedback feedback) {
        return new SpeakingSession(
                id,
                userId,
                language,
                level,
                topicId,
                SpeakingSessionStatus.FINISHED,
                currentPrompt,
                responses,
                responseCount,
                totalWordCount,
                totalSentenceCount,
                startedAt,
                Instant.now(),
                feedback
        );
    }

    private static int countWords(String content) {
        String normalized = content == null ? "" : content.trim();
        return normalized.isBlank() ? 0 : normalized.split("\\s+").length;
    }

    private static int countSentences(String content) {
        String normalized = content == null ? "" : content.trim();
        if (normalized.isBlank()) {
            return 0;
        }

        return (int) java.util.Arrays.stream(normalized.split("[.!?]+"))
                .filter(part -> !part.isBlank())
                .count();
    }
}
