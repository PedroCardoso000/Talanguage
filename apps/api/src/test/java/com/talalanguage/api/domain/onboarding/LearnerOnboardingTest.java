package com.talalanguage.api.domain.onboarding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.SkillType;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;

class LearnerOnboardingTest {
    private static final UserId USER_ID = UserId.from("user-1");
    private static final Instant NOW = Instant.parse("2026-07-16T18:00:00Z");

    @Test
    void shouldCreateAndUpdateValidOnboardingWithoutChangingCompletionTime() {
        var created = valid(null, Occupation.OTHER, "Software architect", NOW);
        var updated = LearnerOnboarding.save(USER_ID, created, AgeRange.AGE_35_44, Occupation.EMPLOYED,
                "must be discarded", List.of(LearningMotivation.CAREER), "Lead meetings",
                List.of(SkillType.SPEAKING), CurrentLevel.ADVANCED, 240, NOW.plusSeconds(60));
        assertEquals(NOW, updated.completedAt());
        assertNull(updated.occupationDescription());
    }

    @Test
    void shouldRejectEmptyOrDuplicateLists() {
        assertThrows(IllegalArgumentException.class, () -> LearnerOnboarding.save(USER_ID, null, null,
                Occupation.EMPLOYED, null, List.of(), "Goal", List.of(SkillType.SPEAKING),
                CurrentLevel.BEGINNER, 60, NOW));
        assertThrows(IllegalArgumentException.class, () -> LearnerOnboarding.save(USER_ID, null, null,
                Occupation.EMPLOYED, null, List.of(LearningMotivation.CAREER, LearningMotivation.CAREER),
                "Goal", List.of(SkillType.SPEAKING), CurrentLevel.BEGINNER, 60, NOW));
        assertThrows(IllegalArgumentException.class, () -> LearnerOnboarding.save(USER_ID, null, null,
                Occupation.EMPLOYED, null, List.of(LearningMotivation.CAREER), "Goal",
                List.of(SkillType.SPEAKING, SkillType.SPEAKING), CurrentLevel.BEGINNER, 60, NOW));
    }

    @Test
    void shouldRequireDescriptionForOtherAndValidateAvailability() {
        assertThrows(IllegalArgumentException.class, () -> valid(null, Occupation.OTHER, null, NOW));
        assertThrows(IllegalArgumentException.class, () -> LearnerOnboarding.save(USER_ID, null, null,
                Occupation.EMPLOYED, null, List.of(LearningMotivation.CAREER), "Goal",
                List.of(SkillType.WRITING), CurrentLevel.BEGINNER, 0, NOW));
    }

    private LearnerOnboarding valid(LearnerOnboarding existing, Occupation occupation, String description, Instant now) {
        return LearnerOnboarding.save(USER_ID, existing, AgeRange.AGE_25_34, occupation, description,
                List.of(LearningMotivation.CAREER), "Participate in meetings",
                List.of(SkillType.SPEAKING), CurrentLevel.INTERMEDIATE, 180, now);
    }
}
