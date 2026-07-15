package com.talalanguage.api.web.progress.dto;

import com.talalanguage.api.application.progress.model.WeeklyProgressSummaryView;
import java.util.List;

public record WeeklyProgressSummaryResponseDto(
        String weekStart,
        String weekEnd,
        int activitiesCompleted,
        int streakDays,
        ProgressSummaryResponseDto.SkillProgressResponseDto skillProgress,
        List<Integer> dailyActivityCounts
) {

    public static WeeklyProgressSummaryResponseDto from(WeeklyProgressSummaryView view) {
        return new WeeklyProgressSummaryResponseDto(
                view.weekStart(),
                view.weekEnd(),
                view.activitiesCompleted(),
                view.streakDays(),
                ProgressSummaryResponseDto.SkillProgressResponseDto.from(view.skillProgress()),
                view.dailyActivityCounts()
        );
    }
}
