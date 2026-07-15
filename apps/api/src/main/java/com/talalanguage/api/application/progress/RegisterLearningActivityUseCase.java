package com.talalanguage.api.application.progress;

import com.talalanguage.api.application.progress.port.LearningActivityRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.LearningActivity;
import com.talalanguage.api.domain.progress.SkillType;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class RegisterLearningActivityUseCase {

    private final LearningActivityRepository learningActivityRepository;

    public RegisterLearningActivityUseCase(LearningActivityRepository learningActivityRepository) {
        this.learningActivityRepository = learningActivityRepository;
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

        return learningActivityRepository.save(activity);
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
