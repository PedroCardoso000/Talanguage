package com.talalanguage.api.application.flashcards;

import com.talalanguage.api.application.flashcards.exception.FlashcardNotFoundException;
import com.talalanguage.api.application.flashcards.model.FlashcardReviewView;
import com.talalanguage.api.application.flashcards.port.FlashcardRepository;
import com.talalanguage.api.application.flashcards.port.FlashcardStatsRepository;
import com.talalanguage.api.application.flashcards.port.ReviewSchedulePolicy;
import com.talalanguage.api.application.progress.RegisterLearningActivityUseCase;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.flashcards.Flashcard;
import com.talalanguage.api.domain.flashcards.ReviewRating;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.SkillType;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class ReviewFlashcardUseCase {

    private final FlashcardRepository flashcardRepository;
    private final ReviewSchedulePolicy reviewSchedulePolicy;
    private final FlashcardStatsRepository flashcardStatsRepository;
    private final RegisterLearningActivityUseCase registerLearningActivityUseCase;

    public ReviewFlashcardUseCase(
            FlashcardRepository flashcardRepository,
            ReviewSchedulePolicy reviewSchedulePolicy,
            FlashcardStatsRepository flashcardStatsRepository,
            RegisterLearningActivityUseCase registerLearningActivityUseCase
    ) {
        this.flashcardRepository = flashcardRepository;
        this.reviewSchedulePolicy = reviewSchedulePolicy;
        this.flashcardStatsRepository = flashcardStatsRepository;
        this.registerLearningActivityUseCase = registerLearningActivityUseCase;
    }

    public FlashcardReviewView execute(Command command) {
        UserId userId = UserId.from(command.userId());
        Flashcard flashcard = flashcardRepository.findByIdAndUserId(command.flashcardId(), userId)
                .orElseThrow(FlashcardNotFoundException::new);

        Flashcard reviewedFlashcard = flashcard.reviewed(
                command.rating(),
                reviewSchedulePolicy.nextReviewAt(command.rating(), Instant.now())
        );

        flashcardRepository.save(reviewedFlashcard);
        flashcardStatsRepository.recordReview(userId, command.rating());
        registerLearningActivityUseCase.execute(new RegisterLearningActivityUseCase.Command(
                userId.value(),
                ActivityType.FLASHCARDS,
                SkillType.VOCABULARY,
                switch (command.rating()) {
                    case AGAIN -> 25;
                    case HARD -> 50;
                    case GOOD -> 75;
                    case EASY -> 90;
                },
                Instant.now(),
                reviewedFlashcard.id()
        ));

        return FlashcardReviewView.from(reviewedFlashcard);
    }

    public record Command(
            String userId,
            String flashcardId,
            ReviewRating rating
    ) {
    }
}
