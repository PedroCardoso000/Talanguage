package com.talalanguage.api.web.goals.dto;

import com.talalanguage.api.application.goals.model.GoalSettingsView;

public record GoalSettingsResponseDto(
        int dailyMinutes,
        int weeklyMinutes,
        int wordsPerDay,
        int challengesPerWeek
) {

    public static GoalSettingsResponseDto from(GoalSettingsView view) {
        return new GoalSettingsResponseDto(
                view.dailyMinutes(),
                view.weeklyMinutes(),
                view.wordsPerDay(),
                view.challengesPerWeek()
        );
    }
}
