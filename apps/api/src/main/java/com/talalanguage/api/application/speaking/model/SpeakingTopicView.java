package com.talalanguage.api.application.speaking.model;

import com.talalanguage.api.domain.speaking.SpeakingTopic;

public record SpeakingTopicView(
        String id,
        String title,
        String language,
        String level,
        String category,
        String objective,
        String mentorMessage,
        String initialPrompt
) {
    public static SpeakingTopicView from(SpeakingTopic topic) {
        return new SpeakingTopicView(
                topic.id(),
                topic.title(),
                topic.language().name(),
                topic.level().name(),
                topic.category(),
                topic.objective(),
                topic.mentorMessage(),
                topic.initialPrompt()
        );
    }
}
