package com.talalanguage.api.application.progress.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.DailyGoal;
import java.time.LocalDate;

public interface DailyGoalRepository {

    DailyGoal getForDate(UserId userId, LocalDate date);
}
