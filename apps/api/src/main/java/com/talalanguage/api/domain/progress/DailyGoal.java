package com.talalanguage.api.domain.progress;

import com.talalanguage.api.domain.auth.UserId;
import java.time.LocalDate;
import java.util.Objects;

public record DailyGoal(
        String id,
        UserId userId,
        int targetActivities,
        LocalDate date
) {

    public DailyGoal {
        Objects.requireNonNull(id, "Goal id is required.");
        Objects.requireNonNull(userId, "User id is required.");
        Objects.requireNonNull(date, "Goal date is required.");

        if (id.isBlank()) {
            throw new IllegalArgumentException("Goal id is required.");
        }

        if (targetActivities <= 0) {
            throw new IllegalArgumentException("Target activities must be greater than zero.");
        }
    }
}
