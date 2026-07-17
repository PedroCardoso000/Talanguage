package com.talalanguage.api.application.auth.model;

import com.talalanguage.api.domain.auth.User;

public record AuthUserView(
        String id,
        String name,
        String email,
        String targetLanguage,
        String currentLevel,
        String studyGoal,
        String avatarUrl,
        boolean onboardingCompleted
) {

    public static AuthUserView from(User user, boolean onboardingCompleted) {
        return new AuthUserView(
                user.id().value(),
                user.name(),
                user.email().value(),
                user.targetLanguage(),
                user.currentLevel(),
                user.studyGoal(),
                user.avatarUrl(),
                onboardingCompleted
        );
    }

    public static AuthUserView from(User user) {
        return from(user, false);
    }
}
