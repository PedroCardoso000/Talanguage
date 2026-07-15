package com.talalanguage.api.web.profile.dto;

import com.talalanguage.api.application.profile.model.ProfileView;

public record ProfileResponseDto(
        String id,
        String name,
        String email,
        String targetLanguage,
        String currentLevel,
        String studyGoal,
        String avatarUrl
) {

    public static ProfileResponseDto from(ProfileView view) {
        return new ProfileResponseDto(
                view.id(),
                view.name(),
                view.email(),
                view.targetLanguage(),
                view.currentLevel(),
                view.studyGoal(),
                view.avatarUrl()
        );
    }
}
