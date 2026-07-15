package com.talalanguage.api.application.progress.port;

import com.talalanguage.api.application.progress.model.ProgressSummaryView;
import com.talalanguage.api.application.progress.model.WeeklyProgressSummaryView;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.DailyGoal;
import com.talalanguage.api.domain.progress.LearningActivity;
import java.util.List;

public interface ProgressCalculator {

    ProgressSummaryView calculateSummary(UserId userId, List<LearningActivity> activities, DailyGoal dailyGoal);

    WeeklyProgressSummaryView calculateWeeklySummary(UserId userId, List<LearningActivity> activities);
}
