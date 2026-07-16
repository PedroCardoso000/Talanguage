package com.talalanguage.api.domain.onboarding;

public enum CurrentLevel {
    BEGINNER, INTERMEDIATE, ADVANCED;

    public static CurrentLevel from(String value) {
        try { return value == null ? null : valueOf(value); }
        catch (IllegalArgumentException exception) { throw new IllegalArgumentException("Current level is invalid."); }
    }
}
