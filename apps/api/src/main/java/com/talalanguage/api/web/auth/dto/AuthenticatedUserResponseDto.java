package com.talalanguage.api.web.auth.dto;

import com.talalanguage.api.application.auth.model.AuthUserView;

public record AuthenticatedUserResponseDto(String id, String name, String email, String targetLanguage,
        String currentLevel, String studyGoal, String avatarUrl, boolean onboardingCompleted) {
    public static AuthenticatedUserResponseDto from(AuthUserView user) {
        return new AuthenticatedUserResponseDto(user.id(), user.name(), user.email(), user.targetLanguage(),
                user.currentLevel(), user.studyGoal(), user.avatarUrl(), user.onboardingCompleted());
    }
}
