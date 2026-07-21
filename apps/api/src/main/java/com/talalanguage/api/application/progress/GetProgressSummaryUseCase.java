package com.talalanguage.api.application.progress;

import com.talalanguage.api.application.progress.model.ProgressSummaryView;
import com.talalanguage.api.application.progress.port.DailyGoalRepository;
import com.talalanguage.api.application.progress.port.LearningActivityRepository;
import com.talalanguage.api.application.progress.port.ProgressCalculator;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.LearningDayPolicy;
import java.time.Clock;
import org.springframework.stereotype.Service;

@Service
public class GetProgressSummaryUseCase {

    private final LearningActivityRepository learningActivityRepository;
    private final DailyGoalRepository dailyGoalRepository;
    private final ProgressCalculator progressCalculator;
    private final Clock clock;

    public GetProgressSummaryUseCase(
            LearningActivityRepository learningActivityRepository,
            DailyGoalRepository dailyGoalRepository,
            ProgressCalculator progressCalculator,
            Clock clock
    ) {
        this.learningActivityRepository = learningActivityRepository;
        this.dailyGoalRepository = dailyGoalRepository;
        this.progressCalculator = progressCalculator;
        this.clock = clock;
    }

    public ProgressSummaryView execute(Command command) {
        UserId userId = UserId.from(command.userId());
        return progressCalculator.calculateSummary(
                userId,
                learningActivityRepository.findByUserId(userId),
                dailyGoalRepository.getForDate(userId, LearningDayPolicy.today(clock))
        );
    }

    public record Command(String userId) {
    }
}
