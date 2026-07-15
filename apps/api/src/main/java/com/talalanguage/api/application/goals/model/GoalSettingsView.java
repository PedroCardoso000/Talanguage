package com.talalanguage.api.application.goals.model;

import com.talalanguage.api.domain.goals.GoalSettings;

public record GoalSettingsView(
        int dailyMinutes,
        int weeklyMinutes,
        int wordsPerDay,
        int challengesPerWeek
) {

    public static GoalSettingsView from(GoalSettings goalSettings) {
        return new GoalSettingsView(
                goalSettings.dailyMinutes(),
                goalSettings.weeklyMinutes(),
                goalSettings.wordsPerDay(),
                goalSettings.challengesPerWeek()
        );
    }
}
