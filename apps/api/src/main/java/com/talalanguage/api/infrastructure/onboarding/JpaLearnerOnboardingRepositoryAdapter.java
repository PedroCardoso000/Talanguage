package com.talalanguage.api.infrastructure.onboarding;

import com.talalanguage.api.application.onboarding.port.LearnerOnboardingRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.onboarding.AgeRange;
import com.talalanguage.api.domain.onboarding.CurrentLevel;
import com.talalanguage.api.domain.onboarding.LearnerOnboarding;
import com.talalanguage.api.domain.onboarding.LearningMotivation;
import com.talalanguage.api.domain.onboarding.Occupation;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.infrastructure.persistence.PersistenceJsonCodec;
import com.talalanguage.api.infrastructure.persistence.entity.LearnerOnboardingEntity;
import com.talalanguage.api.infrastructure.persistence.repository.LearnerOnboardingJpaRepository;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaLearnerOnboardingRepositoryAdapter implements LearnerOnboardingRepository {
    private final LearnerOnboardingJpaRepository repository;
    private final PersistenceJsonCodec jsonCodec;

    public JpaLearnerOnboardingRepositoryAdapter(LearnerOnboardingJpaRepository repository, PersistenceJsonCodec jsonCodec) {
        this.repository = repository; this.jsonCodec = jsonCodec;
    }

    @Override
    public Optional<LearnerOnboarding> findByUserId(UserId userId) {
        return repository.findById(userId.value()).map(this::toDomain);
    }

    @Override
    public LearnerOnboarding save(LearnerOnboarding value) {
        repository.save(new LearnerOnboardingEntity(value.userId().value(),
                value.ageRange() == null ? null : value.ageRange().apiValue(), value.occupation().name(),
                value.occupationDescription(), jsonCodec.writeStringList(value.learningMotivations().stream().map(Enum::name).toList()),
                value.primaryGoal(), jsonCodec.writeStringList(value.difficultySkills().stream().map(Enum::name).toList()),
                value.currentLevel().name(), value.weeklyAvailabilityMinutes(), value.completedAt(), value.updatedAt()));
        return value;
    }

    @Override
    public boolean existsByUserId(UserId userId) { return repository.existsById(userId.value()); }

    private LearnerOnboarding toDomain(LearnerOnboardingEntity entity) {
        return new LearnerOnboarding(UserId.from(entity.getUserId()), AgeRange.fromApiValue(entity.getAgeRange()),
                Occupation.from(entity.getOccupation()), entity.getOccupationDescription(),
                jsonCodec.readStringList(entity.getLearningMotivationsJson()).stream().map(LearningMotivation::from).toList(),
                entity.getPrimaryGoal(), jsonCodec.readStringList(entity.getDifficultySkillsJson()).stream().map(SkillType::valueOf).toList(),
                CurrentLevel.from(entity.getCurrentLevel()), entity.getWeeklyAvailabilityMinutes(),
                entity.getCompletedAt(), entity.getUpdatedAt());
    }
}
