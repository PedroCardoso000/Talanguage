package com.talalanguage.api.application.dashboard.model;

import com.talalanguage.api.application.progress.model.SkillProgressView;
import java.util.List;
import java.util.Map;

public record DashboardSummaryView(
        String userName,
        int currentStreakDays,
        int completedActivitiesToday,
        int weeklyActivityCount,
        int goalCompletionPercent,
        Map<String, Integer> moduleProgress,
        List<Integer> weeklyActivity,
        DailyGoalView dailyGoal,
        SkillProgressView skillProgress,
        List<RecentActivityView> recentActivities,
        NextSuggestedActionView nextSuggestedAction
) {
    public record DailyGoalView(
            int target,
            int completed
    ) {
    }

    public record RecentActivityView(
            String id,
            String type,
            String title,
            String completedAt
    ) {
    }

    public record NextSuggestedActionView(
            String type,
            String label,
            String route
    ) {
    }
}
