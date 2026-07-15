package com.talalanguage.api.domain.speaking;

public record SpeakingFeedback(
        int score,
        String feedback,
        String nextAction
) {
}
