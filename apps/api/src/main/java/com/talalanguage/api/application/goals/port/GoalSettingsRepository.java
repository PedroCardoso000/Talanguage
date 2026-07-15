package com.talalanguage.api.application.goals.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.goals.GoalSettings;

public interface GoalSettingsRepository {

    GoalSettings getByUserId(UserId userId);

    GoalSettings save(GoalSettings goalSettings);
}
