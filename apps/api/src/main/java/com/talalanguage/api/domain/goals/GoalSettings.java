package com.talalanguage.api.domain.goals;

import com.talalanguage.api.domain.auth.UserId;
import java.util.Objects;

public record GoalSettings(
        UserId userId,
        int dailyMinutes,
        int weeklyMinutes,
        int wordsPerDay,
        int challengesPerWeek
) {

    public GoalSettings {
        Objects.requireNonNull(userId, "User id is required.");
        validatePositive(dailyMinutes, "Daily minutes");
        validatePositive(weeklyMinutes, "Weekly minutes");
        validatePositive(wordsPerDay, "Words per day");
        validatePositive(challengesPerWeek, "Challenges per week");
    }

    private static void validatePositive(int value, String field) {
        if (value <= 0) {
            throw new IllegalArgumentException(field + " must be greater than zero.");
        }
    }
}
