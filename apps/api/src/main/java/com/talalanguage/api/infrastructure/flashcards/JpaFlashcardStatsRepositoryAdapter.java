package com.talalanguage.api.infrastructure.flashcards;

import com.talalanguage.api.application.flashcards.model.FlashcardStatsView;
import com.talalanguage.api.application.flashcards.port.FlashcardStatsRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.flashcards.ReviewRating;
import com.talalanguage.api.infrastructure.persistence.entity.FlashcardReviewStatsEntity;
import com.talalanguage.api.infrastructure.persistence.repository.FlashcardReviewStatsJpaRepository;
import java.time.Instant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaFlashcardStatsRepositoryAdapter implements FlashcardStatsRepository {

    private final FlashcardReviewStatsJpaRepository flashcardReviewStatsJpaRepository;

    public JpaFlashcardStatsRepositoryAdapter(FlashcardReviewStatsJpaRepository flashcardReviewStatsJpaRepository) {
        this.flashcardReviewStatsJpaRepository = flashcardReviewStatsJpaRepository;
    }

    @Override
    public FlashcardStatsView getByUserId(UserId userId) {
        FlashcardReviewStatsEntity entity = flashcardReviewStatsJpaRepository.findById(userId.value())
                .orElse(new FlashcardReviewStatsEntity(userId.value(), 0, 0, 0, Instant.now()));

        return new FlashcardStatsView(
                entity.getReviewedCount(),
                entity.getCorrectCount(),
                entity.getWrongCount(),
                calculateAccuracyPercent(entity.getCorrectCount(), entity.getReviewedCount())
        );
    }

    @Override
    public void recordReview(UserId userId, ReviewRating rating) {
        FlashcardReviewStatsEntity current = flashcardReviewStatsJpaRepository.findById(userId.value())
                .orElse(new FlashcardReviewStatsEntity(userId.value(), 0, 0, 0, Instant.now()));

        int reviewed = current.getReviewedCount() + 1;
        int correct = current.getCorrectCount() + (rating == ReviewRating.AGAIN ? 0 : 1);
        int wrong = current.getWrongCount() + (rating == ReviewRating.AGAIN ? 1 : 0);

        flashcardReviewStatsJpaRepository.save(new FlashcardReviewStatsEntity(
                userId.value(),
                reviewed,
                correct,
                wrong,
                Instant.now()
        ));
    }

    private int calculateAccuracyPercent(int correctCount, int reviewedCount) {
        if (reviewedCount <= 0) {
            return 0;
        }

        return (int) Math.round((correctCount * 100.0) / reviewedCount);
    }
}
