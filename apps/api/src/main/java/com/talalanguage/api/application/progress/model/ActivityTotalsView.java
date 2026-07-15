package com.talalanguage.api.application.progress.model;

public record ActivityTotalsView(
        int daysPracticed,
        int speakingSessions,
        int writingSubmissions,
        int flashcardReviews,
        int mockTestsCompleted,
        int goalsUpdated
) {
}
