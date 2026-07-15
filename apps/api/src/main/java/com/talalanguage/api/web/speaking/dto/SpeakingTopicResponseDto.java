package com.talalanguage.api.web.speaking.dto;

import com.talalanguage.api.application.speaking.model.SpeakingTopicView;

public record SpeakingTopicResponseDto(
        String id,
        String title,
        String language,
        String level,
        String category,
        String objective,
        String mentorMessage,
        String initialPrompt
) {
    public static SpeakingTopicResponseDto from(SpeakingTopicView view) {
        return new SpeakingTopicResponseDto(
                view.id(),
                view.title(),
                view.language(),
                view.level(),
                view.category(),
                view.objective(),
                view.mentorMessage(),
                view.initialPrompt()
        );
    }
}
