package com.talalanguage.api.domain.writing;

import java.util.List;
import java.util.Objects;

public record WritingFeedback(
        int score,
        List<String> strengths,
        List<String> improvements,
        String nextAction
) {

    public WritingFeedback {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        Objects.requireNonNull(strengths, "Strengths are required.");
        if (strengths.isEmpty()) {
            throw new IllegalArgumentException("At least one strength is required.");
        }
        strengths = List.copyOf(strengths);
        Objects.requireNonNull(improvements, "Improvements are required.");
        if (improvements.isEmpty()) {
            throw new IllegalArgumentException("At least one improvement is required.");
        }
        improvements = List.copyOf(improvements);
        nextAction = requireText(nextAction, "Next action is required.");
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
