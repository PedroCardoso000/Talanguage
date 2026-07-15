package com.talalanguage.api.application.goals;

import com.talalanguage.api.application.goals.model.GoalSettingsView;
import com.talalanguage.api.application.goals.port.GoalSettingsRepository;
import com.talalanguage.api.domain.auth.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetGoalSettingsUseCase {

    private final GoalSettingsRepository goalSettingsRepository;

    public GetGoalSettingsUseCase(GoalSettingsRepository goalSettingsRepository) {
        this.goalSettingsRepository = goalSettingsRepository;
    }

    public GoalSettingsView execute(Command command) {
        return GoalSettingsView.from(goalSettingsRepository.getByUserId(UserId.from(command.userId())));
    }

    public record Command(String userId) {
    }
}
