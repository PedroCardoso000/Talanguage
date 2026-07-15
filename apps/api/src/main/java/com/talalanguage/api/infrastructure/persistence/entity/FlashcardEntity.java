package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "flashcard")
public class FlashcardEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;

    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Column(name = "front_text", nullable = false)
    private String frontText;

    @Column(name = "back_text", nullable = false)
    private String backText;

    @Column(nullable = false, length = 32)
    private String language;

    @Column(name = "tags_json", nullable = false, columnDefinition = "text")
    private String tagsJson;

    @Column(nullable = false)
    private int difficulty;

    @Column(name = "review_count", nullable = false)
    private int reviewCount;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "next_review_at", nullable = false)
    private Instant nextReviewAt;

    protected FlashcardEntity() {
    }

    public FlashcardEntity(
            String id,
            String userId,
            String frontText,
            String backText,
            String language,
            String tagsJson,
            int difficulty,
            int reviewCount,
            Instant createdAt,
            Instant nextReviewAt
    ) {
        this.id = id;
        this.userId = userId;
        this.frontText = frontText;
        this.backText = backText;
        this.language = language;
        this.tagsJson = tagsJson;
        this.difficulty = difficulty;
        this.reviewCount = reviewCount;
        this.createdAt = createdAt;
        this.nextReviewAt = nextReviewAt;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getFrontText() {
        return frontText;
    }

    public String getBackText() {
        return backText;
    }

    public String getLanguage() {
        return language;
    }

    public String getTagsJson() {
        return tagsJson;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getNextReviewAt() {
        return nextReviewAt;
    }
}
