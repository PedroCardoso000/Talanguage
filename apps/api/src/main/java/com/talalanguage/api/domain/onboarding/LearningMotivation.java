package com.talalanguage.api.domain.onboarding;

public enum LearningMotivation {
    CAREER, TRAVEL, STUDY, IMMIGRATION, CULTURE, PERSONAL_DEVELOPMENT, OTHER;

    public static LearningMotivation from(String value) {
        try { return valueOf(value); }
        catch (RuntimeException exception) { throw new IllegalArgumentException("Learning motivation is invalid."); }
    }
}
