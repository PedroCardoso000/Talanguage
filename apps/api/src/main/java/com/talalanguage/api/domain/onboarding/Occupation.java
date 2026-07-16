package com.talalanguage.api.domain.onboarding;

public enum Occupation {
    STUDENT, EMPLOYED, SELF_EMPLOYED, UNEMPLOYED, RETIRED, OTHER;

    public static Occupation from(String value) {
        try { return value == null ? null : valueOf(value); }
        catch (IllegalArgumentException exception) { throw new IllegalArgumentException("Occupation is invalid."); }
    }
}
