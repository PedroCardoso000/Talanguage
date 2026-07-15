package com.talalanguage.api.infrastructure.goals;

import com.talalanguage.api.application.goals.port.GoalSettingsRepository;
import com.talalanguage.api.application.progress.port.DailyGoalRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.goals.GoalSettings;
import com.talalanguage.api.domain.progress.DailyGoal;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryGoalSettingsRepository implements GoalSettingsRepository, DailyGoalRepository {

    private static final GoalSettings DEFAULT_GOALS = new GoalSettings(
            UserId.from("default-user"),
            25,
            180,
            20,
            4
    );

    private final Map<String, GoalSettings> goalsByUserId = new ConcurrentHashMap<>();

    @Override
    public GoalSettings getByUserId(UserId userId) {
        return goalsByUserId.computeIfAbsent(userId.value(), ignored ->
                new GoalSettings(
                        userId,
                        DEFAULT_GOALS.dailyMinutes(),
                        DEFAULT_GOALS.weeklyMinutes(),
                        DEFAULT_GOALS.wordsPerDay(),
                        DEFAULT_GOALS.challengesPerWeek()
                )
        );
    }

    @Override
    public GoalSettings save(GoalSettings goalSettings) {
        goalsByUserId.put(goalSettings.userId().value(), goalSettings);
        return goalSettings;
    }

    @Override
    public DailyGoal getForDate(UserId userId, LocalDate date) {
        GoalSettings settings = getByUserId(userId);
        return new DailyGoal(
                "goal-" + userId.value() + "-" + date,
                userId,
                settings.challengesPerWeek(),
                date
        );
    }

    public void clear() {
        goalsByUserId.clear();
    }
}
