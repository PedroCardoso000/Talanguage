package com.talalanguage.api.application.goals;

import com.talalanguage.api.application.goals.model.GoalSettingsView;
import com.talalanguage.api.application.goals.port.GoalSettingsRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.goals.GoalSettings;
import org.springframework.stereotype.Service;

@Service
public class UpdateGoalSettingsUseCase {

    private final GoalSettingsRepository goalSettingsRepository;

    public UpdateGoalSettingsUseCase(GoalSettingsRepository goalSettingsRepository) {
        this.goalSettingsRepository = goalSettingsRepository;
    }

    public GoalSettingsView execute(Command command) {
        GoalSettings goalSettings = new GoalSettings(
                UserId.from(command.userId()),
                command.dailyMinutes(),
                command.weeklyMinutes(),
                command.wordsPerDay(),
                command.challengesPerWeek()
        );

        return GoalSettingsView.from(goalSettingsRepository.save(goalSettings));
    }

    public record Command(
            String userId,
            int dailyMinutes,
            int weeklyMinutes,
            int wordsPerDay,
            int challengesPerWeek
    ) {
    }
}
