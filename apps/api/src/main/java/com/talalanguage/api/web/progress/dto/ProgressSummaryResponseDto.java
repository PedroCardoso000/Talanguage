package com.talalanguage.api.web.progress.dto;

import com.talalanguage.api.application.progress.model.ActivityTotalsView;
import com.talalanguage.api.application.progress.model.DailyGoalView;
import com.talalanguage.api.application.progress.model.ProgressSummaryView;
import com.talalanguage.api.application.progress.model.SkillProgressView;

public record ProgressSummaryResponseDto(
        int streakDays,
        int longestStreakDays,
        String lastActivityDate,
        DailyGoalResponseDto dailyGoal,
        SkillProgressResponseDto skillProgress,
        int totalActivities,
        ActivityTotalsResponseDto activityTotals
) {

    public static ProgressSummaryResponseDto from(ProgressSummaryView view) {
        return new ProgressSummaryResponseDto(
                view.streakDays(),
                view.longestStreakDays(),
                view.lastActivityDate(),
                DailyGoalResponseDto.from(view.dailyGoal()),
                SkillProgressResponseDto.from(view.skillProgress()),
                view.totalActivities(),
                ActivityTotalsResponseDto.from(view.activityTotals())
        );
    }

    public record DailyGoalResponseDto(
            int target,
            int completed,
            String status
    ) {
        static DailyGoalResponseDto from(DailyGoalView view) {
            return new DailyGoalResponseDto(view.target(), view.completed(), view.status());
        }
    }

    public record SkillProgressResponseDto(
            int speaking,
            int writing,
            int vocabulary,
            int consistency
    ) {
        public static SkillProgressResponseDto from(SkillProgressView view) {
            return new SkillProgressResponseDto(view.speaking(), view.writing(), view.vocabulary(), view.consistency());
        }
    }

    public record ActivityTotalsResponseDto(
            int daysPracticed,
            int speakingSessions,
            int writingSubmissions,
            int flashcardReviews,
            int mockTestsCompleted,
            int goalsUpdated
    ) {
        public static ActivityTotalsResponseDto from(ActivityTotalsView view) {
            return new ActivityTotalsResponseDto(
                    view.daysPracticed(),
                    view.speakingSessions(),
                    view.writingSubmissions(),
                    view.flashcardReviews(),
                    view.mockTestsCompleted(),
                    view.goalsUpdated()
            );
        }
    }
}
