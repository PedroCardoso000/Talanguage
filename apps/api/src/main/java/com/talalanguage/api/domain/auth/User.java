package com.talalanguage.api.domain.auth;

import java.time.Instant;
import java.util.Objects;

public final class User {

    private static final int MAX_STUDY_GOAL_LENGTH = 240;

    private final UserId id;
    private final String name;
    private final Email email;
    private final String passwordHash;
    private final String targetLanguage;
    private final String currentLevel;
    private final String studyGoal;
    private final String avatarUrl;
    private final Instant createdAt;
    private final Instant updatedAt;

    private User(
            UserId id,
            String name,
            Email email,
            String passwordHash,
            String targetLanguage,
            String currentLevel,
            String studyGoal,
            String avatarUrl,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = Objects.requireNonNull(id, "User id is required.");
        this.name = validateName(name);
        this.email = Objects.requireNonNull(email, "Email is required.");
        this.passwordHash = validatePasswordHash(passwordHash);
        this.targetLanguage = validateTargetLanguage(targetLanguage);
        this.currentLevel = validateCurrentLevel(currentLevel);
        this.studyGoal = validateStudyGoal(studyGoal);
        this.avatarUrl = validateAvatarUrl(avatarUrl);
        this.createdAt = Objects.requireNonNull(createdAt, "Created at is required.");
        this.updatedAt = Objects.requireNonNull(updatedAt, "Updated at is required.");
    }

    public static User create(String name, Email email, String passwordHash, String targetLanguage) {
        Instant now = Instant.now();
        return new User(UserId.create(), name, email, passwordHash, targetLanguage, null, null, null, now, now);
    }

    public static User restore(
            UserId id,
            String name,
            Email email,
            String passwordHash,
            String targetLanguage,
            String currentLevel,
            String studyGoal,
            String avatarUrl,
            Instant createdAt,
            Instant updatedAt
    ) {
        return new User(id, name, email, passwordHash, targetLanguage, currentLevel, studyGoal, avatarUrl, createdAt,
                updatedAt);
    }

    public User updateProfile(String name, String targetLanguage, String currentLevel, String studyGoal, String avatarUrl) {
        return new User(
                id,
                name,
                email,
                passwordHash,
                targetLanguage,
                currentLevel,
                studyGoal,
                avatarUrl,
                createdAt,
                Instant.now()
        );
    }

    private static String validateName(String value) {
        Objects.requireNonNull(value, "Name is required.");
        String normalized = value.trim();

        if (normalized.length() < 2) {
            throw new IllegalArgumentException("Name must have at least 2 characters.");
        }

        return normalized;
    }

    private static String validatePasswordHash(String value) {
        Objects.requireNonNull(value, "Password hash is required.");

        if (value.isBlank()) {
            throw new IllegalArgumentException("Password hash is required.");
        }

        return value;
    }

    private static String validateTargetLanguage(String value) {
        String normalized = normalizeOptional(value);

        if (normalized == null) {
            return null;
        }

        if (!normalized.equals("ENGLISH") && !normalized.equals("SPANISH") && !normalized.equals("FRENCH")) {
            throw new IllegalArgumentException("Target language is invalid.");
        }

        return normalized;
    }

    private static String validateCurrentLevel(String value) {
        String normalized = normalizeOptional(value);

        if (normalized == null) {
            return null;
        }

        if (!normalized.equals("BEGINNER") && !normalized.equals("INTERMEDIATE") && !normalized.equals("ADVANCED")) {
            throw new IllegalArgumentException("Current level is invalid.");
        }

        return normalized;
    }

    private static String validateStudyGoal(String value) {
        String normalized = normalizeOptional(value);

        if (normalized == null) {
            return null;
        }

        if (normalized.length() > MAX_STUDY_GOAL_LENGTH) {
            throw new IllegalArgumentException("Study goal must have at most 240 characters.");
        }

        return normalized;
    }

    private static String validateAvatarUrl(String value) {
        String normalized = normalizeOptional(value);

        if (normalized == null) {
            return null;
        }

        if (!normalized.startsWith("http://") && !normalized.startsWith("https://")) {
            throw new IllegalArgumentException("Avatar URL must start with http:// or https://.");
        }

        return normalized;
    }

    private static String normalizeOptional(String value) {
        if (value == null) {
            return null;
        }

        String normalized = value.trim();
        return normalized.isEmpty() ? null : normalized;
    }

    public UserId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Email email() {
        return email;
    }

    public String passwordHash() {
        return passwordHash;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public String targetLanguage() {
        return targetLanguage;
    }

    public String currentLevel() {
        return currentLevel;
    }

    public String studyGoal() {
        return studyGoal;
    }

    public String avatarUrl() {
        return avatarUrl;
    }
}
