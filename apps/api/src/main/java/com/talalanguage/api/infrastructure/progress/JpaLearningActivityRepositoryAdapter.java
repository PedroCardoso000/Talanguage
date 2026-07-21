package com.talalanguage.api.infrastructure.progress;

import com.talalanguage.api.application.progress.port.LearningActivityRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.LearningActivity;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.infrastructure.persistence.entity.LearningActivityEntity;
import com.talalanguage.api.infrastructure.persistence.repository.LearningActivityJpaRepository;
import java.time.Instant;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaLearningActivityRepositoryAdapter implements LearningActivityRepository {

    private final LearningActivityJpaRepository learningActivityJpaRepository;

    public JpaLearningActivityRepositoryAdapter(LearningActivityJpaRepository learningActivityJpaRepository) {
        this.learningActivityJpaRepository = learningActivityJpaRepository;
    }

    @Override
    public LearningActivity save(LearningActivity activity) {
        var existing = findBySource(activity);
        if (existing != null) {
            return existing;
        }

        try {
            learningActivityJpaRepository.saveAndFlush(toEntity(activity));
            return activity;
        } catch (DataIntegrityViolationException exception) {
            LearningActivity concurrentWinner = findBySource(activity);
            if (concurrentWinner != null) {
                return concurrentWinner;
            }
            throw exception;
        }
    }

    @Override
    public List<LearningActivity> findByUserId(UserId userId) {
        return learningActivityJpaRepository.findByUserIdOrderByCompletedAtAsc(userId.value())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<LearningActivity> findByUserIdBetween(UserId userId, Instant startInclusive, Instant endInclusive) {
        return learningActivityJpaRepository.findByUserIdAndCompletedAtBetweenOrderByCompletedAtAsc(
                        userId.value(),
                        startInclusive,
                        endInclusive
                )
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private LearningActivityEntity toEntity(LearningActivity activity) {
        return new LearningActivityEntity(
                activity.id(),
                activity.userId().value(),
                activity.type().name(),
                activity.skill().name(),
                activity.score(),
                activity.completedAt(),
                activity.sourceId()
        );
    }

    private LearningActivity findBySource(LearningActivity activity) {
        return learningActivityJpaRepository.findByUserIdAndTypeAndSourceId(
                        activity.userId().value(),
                        activity.type().name(),
                        activity.sourceId()
                )
                .map(this::toDomain)
                .orElse(null);
    }

    private LearningActivity toDomain(LearningActivityEntity entity) {
        return LearningActivity.restore(
                entity.getId(),
                UserId.from(entity.getUserId()),
                ActivityType.valueOf(entity.getType()),
                SkillType.valueOf(entity.getSkill()),
                entity.getScore(),
                entity.getCompletedAt(),
                entity.getSourceId()
        );
    }
}
