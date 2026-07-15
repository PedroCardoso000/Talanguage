package com.talalanguage.api.application.progress.model;

public record DailyGoalView(
        int target,
        int completed,
        String status
) {
}
