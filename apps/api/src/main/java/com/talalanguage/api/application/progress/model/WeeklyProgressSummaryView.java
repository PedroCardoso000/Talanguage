package com.talalanguage.api.application.progress.model;

import java.util.List;

public record WeeklyProgressSummaryView(
        String weekStart,
        String weekEnd,
        int activitiesCompleted,
        int streakDays,
        SkillProgressView skillProgress,
        List<Integer> dailyActivityCounts
) {
}
