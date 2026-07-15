package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "flashcard_review_stats")
public class FlashcardReviewStatsEntity {

    @Id
    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Column(name = "reviewed_count", nullable = false)
    private int reviewedCount;

    @Column(name = "correct_count", nullable = false)
    private int correctCount;

    @Column(name = "wrong_count", nullable = false)
    private int wrongCount;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected FlashcardReviewStatsEntity() {
    }

    public FlashcardReviewStatsEntity(String userId, int reviewedCount, int correctCount, int wrongCount, Instant updatedAt) {
        this.userId = userId;
        this.reviewedCount = reviewedCount;
        this.correctCount = correctCount;
        this.wrongCount = wrongCount;
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public int getReviewedCount() {
        return reviewedCount;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
