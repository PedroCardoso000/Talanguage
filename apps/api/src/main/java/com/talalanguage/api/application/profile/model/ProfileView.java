package com.talalanguage.api.application.profile.model;

import com.talalanguage.api.domain.auth.User;

public record ProfileView(
        String id,
        String name,
        String email,
        String targetLanguage,
        String currentLevel,
        String studyGoal,
        String avatarUrl
) {

    public static ProfileView from(User user) {
        return new ProfileView(
                user.id().value(),
                user.name(),
                user.email().value(),
                user.targetLanguage(),
                user.currentLevel(),
                user.studyGoal(),
                user.avatarUrl()
        );
    }
}
