package com.talalanguage.api.application.progress;

import com.talalanguage.api.application.progress.model.WeeklyProgressSummaryView;
import com.talalanguage.api.application.progress.port.LearningActivityRepository;
import com.talalanguage.api.application.progress.port.ProgressCalculator;
import com.talalanguage.api.domain.auth.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetWeeklyProgressSummaryUseCase {

    private final LearningActivityRepository learningActivityRepository;
    private final ProgressCalculator progressCalculator;

    public GetWeeklyProgressSummaryUseCase(
            LearningActivityRepository learningActivityRepository,
            ProgressCalculator progressCalculator
    ) {
        this.learningActivityRepository = learningActivityRepository;
        this.progressCalculator = progressCalculator;
    }

    public WeeklyProgressSummaryView execute(Command command) {
        UserId userId = UserId.from(command.userId());
        return progressCalculator.calculateWeeklySummary(
                userId,
                learningActivityRepository.findByUserId(userId)
        );
    }

    public record Command(String userId) {
    }
}
