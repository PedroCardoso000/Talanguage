package com.talalanguage.api.application.progress.model;

public record ProgressSummaryView(
        int streakDays,
        int longestStreakDays,
        String lastActivityDate,
        DailyGoalView dailyGoal,
        SkillProgressView skillProgress,
        int totalActivities,
        ActivityTotalsView activityTotals
) {
}
