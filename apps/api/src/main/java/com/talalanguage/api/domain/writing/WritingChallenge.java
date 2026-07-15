package com.talalanguage.api.domain.writing;

import java.util.Objects;

public record WritingChallenge(
        String id,
        WritingLanguage language,
        WritingLevel level,
        String prompt,
        String expectedSkill,
        boolean active
) {

    public WritingChallenge {
        id = requireText(id, "Challenge id is required.");
        Objects.requireNonNull(language, "Language is required.");
        Objects.requireNonNull(level, "Level is required.");
        prompt = requireText(prompt, "Prompt is required.");
        expectedSkill = requireText(expectedSkill, "Expected skill is required.");
    }

    private static String requireText(String value, String message) {
        Objects.requireNonNull(value, message);
        String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return normalized;
    }
}
