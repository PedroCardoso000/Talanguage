package com.talalanguage.api.application.progress;

import com.talalanguage.api.application.progress.model.WeeklyProgressSummaryView;
import com.talalanguage.api.application.progress.port.LearningActivityRepository;
import com.talalanguage.api.application.progress.port.ProgressCalculator;
import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
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
        LocalDate today = LocalDate.now(ZoneOffset.UTC);
        Instant start = today.minusDays(6).atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = today.plusDays(1).atStartOfDay().minusNanos(1).toInstant(ZoneOffset.UTC);

        return progressCalculator.calculateWeeklySummary(
                userId,
                learningActivityRepository.findByUserIdBetween(userId, start, end)
        );
    }

    public record Command(String userId) {
    }
}
