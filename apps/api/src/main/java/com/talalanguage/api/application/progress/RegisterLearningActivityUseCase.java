package com.talalanguage.api.application.progress;

import com.talalanguage.api.application.progress.port.LearningActivityRepository;
import com.talalanguage.api.application.notifications.CreateNotificationUseCase;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.LearningActivity;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.domain.notifications.NotificationType;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterLearningActivityUseCase {

    private final LearningActivityRepository learningActivityRepository;
    private final CreateNotificationUseCase createNotificationUseCase;

    @Autowired
    public RegisterLearningActivityUseCase(
            LearningActivityRepository learningActivityRepository,
            CreateNotificationUseCase createNotificationUseCase
    ) {
        this.learningActivityRepository = learningActivityRepository;
        this.createNotificationUseCase = createNotificationUseCase;
    }

    public RegisterLearningActivityUseCase(LearningActivityRepository learningActivityRepository) {
        this(learningActivityRepository, null);
    }

    public LearningActivity execute(Command command) {
        LearningActivity activity = LearningActivity.create(
                UserId.from(command.userId()),
                command.type(),
                command.skill(),
                command.score(),
                command.completedAt() == null ? Instant.now() : command.completedAt(),
                command.sourceId()
        );

        LocalDate previousLatestDay = learningActivityRepository.findByUserId(activity.userId()).stream()
                .map(existing -> utcDate(existing.completedAt()))
                .max(LocalDate::compareTo)
                .orElse(null);

        LearningActivity persisted = learningActivityRepository.save(activity);
        if (persisted.id().equals(activity.id())) {
            notifyStreakLostIfConfirmed(activity, previousLatestDay);
        }
        return persisted;
    }

    private void notifyStreakLostIfConfirmed(LearningActivity activity, LocalDate previousLatestDay) {
        if (createNotificationUseCase == null || previousLatestDay == null) {
            return;
        }
        LocalDate currentDay = utcDate(activity.completedAt());
        if (!currentDay.isAfter(previousLatestDay)
                || ChronoUnit.DAYS.between(previousLatestDay, currentDay) <= 1) {
            return;
        }
        createNotificationUseCase.execute(new CreateNotificationUseCase.Command(
                activity.userId().value(),
                NotificationType.STREAK_LOST,
                "streak-lost:" + currentDay,
                "Sequencia interrompida",
                "A nova atividade confirmou uma ausencia superior a um dia UTC. Comece uma nova sequencia hoje.",
                "/progress"
        ));
    }

    private LocalDate utcDate(Instant instant) {
        return instant.atZone(ZoneOffset.UTC).toLocalDate();
    }

    public record Command(
            String userId,
            ActivityType type,
            SkillType skill,
            int score,
            Instant completedAt,
            String sourceId
    ) {
    }
}
