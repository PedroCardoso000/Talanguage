package com.talalanguage.api.web.auth.dto;

import com.talalanguage.api.application.auth.model.AuthUserView;

public record AuthUserResponseDto(
        String id,
        String name,
        String email,
        String targetLanguage,
        String currentLevel,
        String studyGoal,
        String avatarUrl
) {

    public static AuthUserResponseDto from(AuthUserView user) {
        return new AuthUserResponseDto(
                user.id(),
                user.name(),
                user.email(),
                user.targetLanguage(),
                user.currentLevel(),
                user.studyGoal(),
                user.avatarUrl()
        );
    }
}
