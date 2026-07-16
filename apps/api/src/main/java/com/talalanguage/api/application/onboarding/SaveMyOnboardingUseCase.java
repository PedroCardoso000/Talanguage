package com.talalanguage.api.application.onboarding;

import com.talalanguage.api.application.onboarding.model.OnboardingView;
import com.talalanguage.api.application.onboarding.port.LearnerOnboardingRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.onboarding.AgeRange;
import com.talalanguage.api.domain.onboarding.CurrentLevel;
import com.talalanguage.api.domain.onboarding.LearnerOnboarding;
import com.talalanguage.api.domain.onboarding.LearningMotivation;
import com.talalanguage.api.domain.onboarding.Occupation;
import com.talalanguage.api.domain.progress.SkillType;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SaveMyOnboardingUseCase {
    private final LearnerOnboardingRepository repository;

    public SaveMyOnboardingUseCase(LearnerOnboardingRepository repository) { this.repository = repository; }

    public OnboardingView execute(Command command) {
        UserId userId = UserId.from(command.authenticatedUserId());
        LearnerOnboarding existing = repository.findByUserId(userId).orElse(null);
        LearnerOnboarding saved = LearnerOnboarding.save(userId, existing,
                AgeRange.fromApiValue(command.ageRange()), Occupation.from(command.occupation()),
                command.occupationDescription(), mapMotivations(command.learningMotivations()), command.primaryGoal(),
                mapSkills(command.difficultySkills()), CurrentLevel.from(command.currentLevel()),
                command.weeklyAvailabilityMinutes(), Instant.now());
        return OnboardingView.from(repository.save(saved));
    }

    private List<LearningMotivation> mapMotivations(List<String> values) {
        if (values == null) { return null; }
        return values.stream().map(LearningMotivation::from).toList();
    }

    private List<SkillType> mapSkills(List<String> values) {
        if (values == null) { return null; }
        try { return values.stream().map(SkillType::valueOf).toList(); }
        catch (IllegalArgumentException exception) { throw new IllegalArgumentException("Difficulty skill is invalid."); }
    }

    public record Command(String authenticatedUserId, String ageRange, String occupation,
            String occupationDescription, List<String> learningMotivations, String primaryGoal,
            List<String> difficultySkills, String currentLevel, int weeklyAvailabilityMinutes) { }
}
