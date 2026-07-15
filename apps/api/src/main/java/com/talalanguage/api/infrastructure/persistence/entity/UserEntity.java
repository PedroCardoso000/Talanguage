package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "app_user")
public class UserEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "target_language", length = 32)
    private String targetLanguage;

    @Column(name = "current_level", length = 32)
    private String currentLevel;

    @Column(name = "study_goal", length = 240)
    private String studyGoal;

    @Column(name = "avatar_url", length = 512)
    private String avatarUrl;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected UserEntity() {
    }

    public UserEntity(
            String id,
            String name,
            String email,
            String passwordHash,
            String targetLanguage,
            String currentLevel,
            String studyGoal,
            String avatarUrl,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.targetLanguage = targetLanguage;
        this.currentLevel = currentLevel;
        this.studyGoal = studyGoal;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public String getStudyGoal() {
        return studyGoal;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
