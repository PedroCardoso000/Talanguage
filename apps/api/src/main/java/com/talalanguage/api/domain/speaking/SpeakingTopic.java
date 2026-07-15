package com.talalanguage.api.domain.speaking;

public record SpeakingTopic(
        String id,
        String title,
        SpeakingLanguage language,
        SpeakingLevel level,
        String category,
        String objective,
        String mentorMessage,
        String initialPrompt,
        String followUpPrompt
) {
}
