package com.talalanguage.api.domain.flashcards;

import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class Flashcard {

    private final String id;
    private final UserId userId;
    private final String front;
    private final String back;
    private final FlashcardLanguage language;
    private final List<String> tags;
    private final int difficulty;
    private final int reviewCount;
    private final Instant createdAt;
    private final Instant nextReviewAt;

    private Flashcard(
            String id,
            UserId userId,
            String front,
            String back,
            FlashcardLanguage language,
            List<String> tags,
            int difficulty,
            int reviewCount,
            Instant createdAt,
            Instant nextReviewAt
    ) {
        this.id = requireText(id, "Flashcard id is required.");
        this.userId = Objects.requireNonNull(userId, "User id is required.");
        this.front = requireText(front, "Flashcard front is required.");
        this.back = requireText(back, "Flashcard back is required.");
        this.language = Objects.requireNonNull(language, "Flashcard language is required.");
        this.tags = tags == null ? List.of() : List.copyOf(tags);
        this.difficulty = Math.max(difficulty, 1);
        this.reviewCount = Math.max(reviewCount, 0);
        this.createdAt = Objects.requireNonNull(createdAt, "Created at is required.");
        this.nextReviewAt = Objects.requireNonNull(nextReviewAt, "Next review at is required.");
    }

    public static Flashcard create(
            UserId userId,
            String front,
            String back,
            FlashcardLanguage language,
            List<String> tags,
            Instant nextReviewAt
    ) {
        Instant now = Instant.now();
        return new Flashcard(
                UUID.randomUUID().toString(),
                userId,
                front,
                back,
                language,
                tags,
                1,
                0,
                now,
                nextReviewAt
        );
    }

    public static Flashcard restore(
            String id,
            UserId userId,
            String front,
            String back,
            FlashcardLanguage language,
            List<String> tags,
            int difficulty,
            int reviewCount,
            Instant createdAt,
            Instant nextReviewAt
    ) {
        return new Flashcard(id, userId, front, back, language, tags, difficulty, reviewCount, createdAt, nextReviewAt);
    }

    public Flashcard reviewed(ReviewRating rating, Instant nextReviewAt) {
        int nextDifficulty = switch (rating) {
            case AGAIN -> Math.max(1, difficulty - 1);
            case HARD -> difficulty;
            case GOOD -> difficulty + 1;
            case EASY -> difficulty + 2;
        };

        return new Flashcard(
                id,
                userId,
                front,
                back,
                language,
                tags,
                nextDifficulty,
                reviewCount + 1,
                createdAt,
                nextReviewAt
        );
    }

    private static String requireText(String value, String message) {
        Objects.requireNonNull(value, message);
        String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return normalized;
    }

    public String id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public String front() {
        return front;
    }

    public String back() {
        return back;
    }

    public FlashcardLanguage language() {
        return language;
    }

    public List<String> tags() {
        return tags;
    }

    public int difficulty() {
        return difficulty;
    }

    public int reviewCount() {
        return reviewCount;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant nextReviewAt() {
        return nextReviewAt;
    }
}
