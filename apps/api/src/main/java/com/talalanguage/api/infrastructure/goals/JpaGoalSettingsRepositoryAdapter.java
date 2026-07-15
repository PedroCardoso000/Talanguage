package com.talalanguage.api.infrastructure.goals;

import com.talalanguage.api.application.goals.port.GoalSettingsRepository;
import com.talalanguage.api.application.progress.port.DailyGoalRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.goals.GoalSettings;
import com.talalanguage.api.domain.progress.DailyGoal;
import com.talalanguage.api.infrastructure.persistence.entity.GoalSettingsEntity;
import com.talalanguage.api.infrastructure.persistence.repository.GoalSettingsJpaRepository;
import java.time.Instant;
import java.time.LocalDate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaGoalSettingsRepositoryAdapter implements GoalSettingsRepository, DailyGoalRepository {

    private static final int DEFAULT_DAILY_MINUTES = 25;
    private static final int DEFAULT_WEEKLY_MINUTES = 180;
    private static final int DEFAULT_WORDS_PER_DAY = 20;
    private static final int DEFAULT_CHALLENGES_PER_WEEK = 4;

    private final GoalSettingsJpaRepository goalSettingsJpaRepository;

    public JpaGoalSettingsRepositoryAdapter(GoalSettingsJpaRepository goalSettingsJpaRepository) {
        this.goalSettingsJpaRepository = goalSettingsJpaRepository;
    }

    @Override
    public GoalSettings getByUserId(UserId userId) {
        return goalSettingsJpaRepository.findById(userId.value())
                .map(this::toDomain)
                .orElseGet(() -> save(new GoalSettings(
                        userId,
                        DEFAULT_DAILY_MINUTES,
                        DEFAULT_WEEKLY_MINUTES,
                        DEFAULT_WORDS_PER_DAY,
                        DEFAULT_CHALLENGES_PER_WEEK
                )));
    }

    @Override
    public GoalSettings save(GoalSettings goalSettings) {
        goalSettingsJpaRepository.save(new GoalSettingsEntity(
                goalSettings.userId().value(),
                goalSettings.dailyMinutes(),
                goalSettings.weeklyMinutes(),
                goalSettings.wordsPerDay(),
                goalSettings.challengesPerWeek(),
                Instant.now()
        ));
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

    private GoalSettings toDomain(GoalSettingsEntity entity) {
        return new GoalSettings(
                UserId.from(entity.getUserId()),
                entity.getDailyMinutes(),
                entity.getWeeklyMinutes(),
                entity.getWordsPerDay(),
                entity.getChallengesPerWeek()
        );
    }
}
