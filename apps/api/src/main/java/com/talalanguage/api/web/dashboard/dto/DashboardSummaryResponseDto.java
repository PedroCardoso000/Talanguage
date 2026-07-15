package com.talalanguage.api.web.dashboard.dto;

import com.talalanguage.api.application.dashboard.model.DashboardSummaryView;
import com.talalanguage.api.web.progress.dto.ProgressSummaryResponseDto;
import java.util.List;
import java.util.Map;

public record DashboardSummaryResponseDto(
        String userName,
        int currentStreakDays,
        int completedActivitiesToday,
        int weeklyActivityCount,
        int goalCompletionPercent,
        Map<String, Integer> moduleProgress,
        List<Integer> weeklyActivity,
        DailyGoalResponseDto dailyGoal,
        ProgressSummaryResponseDto.SkillProgressResponseDto skillProgress,
        List<RecentActivityResponseDto> recentActivities,
        NextSuggestedActionResponseDto nextSuggestedAction
) {

    public static DashboardSummaryResponseDto from(DashboardSummaryView view) {
        return new DashboardSummaryResponseDto(
                view.userName(),
                view.currentStreakDays(),
                view.completedActivitiesToday(),
                view.weeklyActivityCount(),
                view.goalCompletionPercent(),
                view.moduleProgress(),
                view.weeklyActivity(),
                new DailyGoalResponseDto(view.dailyGoal().target(), view.dailyGoal().completed()),
                ProgressSummaryResponseDto.SkillProgressResponseDto.from(view.skillProgress()),
                view.recentActivities().stream().map(RecentActivityResponseDto::from).toList(),
                NextSuggestedActionResponseDto.from(view.nextSuggestedAction())
        );
    }

    public record DailyGoalResponseDto(
            int target,
            int completed
    ) {
    }

    public record RecentActivityResponseDto(
            String id,
            String type,
            String title,
            String completedAt
    ) {
        static RecentActivityResponseDto from(DashboardSummaryView.RecentActivityView view) {
            return new RecentActivityResponseDto(view.id(), view.type(), view.title(), view.completedAt());
        }
    }

    public record NextSuggestedActionResponseDto(
            String type,
            String label,
            String route
    ) {
        static NextSuggestedActionResponseDto from(DashboardSummaryView.NextSuggestedActionView view) {
            return new NextSuggestedActionResponseDto(view.type(), view.label(), view.route());
        }
    }
}
