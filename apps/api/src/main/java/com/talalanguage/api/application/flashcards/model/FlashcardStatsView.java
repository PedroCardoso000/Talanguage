package com.talalanguage.api.application.flashcards.model;

public record FlashcardStatsView(
        int reviewedCount,
        int correctCount,
        int wrongCount,
        int overallProgress
) {
}
