package com.talalanguage.api.infrastructure.flashcards;

import com.talalanguage.api.application.flashcards.model.FlashcardStatsView;
import com.talalanguage.api.application.flashcards.port.FlashcardStatsRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.flashcards.ReviewRating;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryFlashcardStatsRepository implements FlashcardStatsRepository {

    private final Map<String, Counter> countersByUserId = new ConcurrentHashMap<>();

    @Override
    public FlashcardStatsView getByUserId(UserId userId) {
        Counter counter = countersByUserId.getOrDefault(userId.value(), new Counter());
        return new FlashcardStatsView(
                counter.reviewedCount,
                counter.correctCount,
                counter.wrongCount,
                calculateAccuracyPercent(counter.correctCount, counter.reviewedCount)
        );
    }

    @Override
    public void recordReview(UserId userId, ReviewRating rating) {
        countersByUserId.compute(userId.value(), (key, current) -> {
            Counter counter = current == null ? new Counter() : current;
            counter.reviewedCount++;
            if (rating == ReviewRating.AGAIN) {
                counter.wrongCount++;
            } else {
                counter.correctCount++;
            }
            return counter;
        });
    }

    public void clear() {
        countersByUserId.clear();
    }

    private int calculateAccuracyPercent(int correctCount, int reviewedCount) {
        if (reviewedCount <= 0) {
            return 0;
        }

        return (int) Math.round((correctCount * 100.0) / reviewedCount);
    }

    private static final class Counter {
        private int reviewedCount;
        private int correctCount;
        private int wrongCount;
    }
}
