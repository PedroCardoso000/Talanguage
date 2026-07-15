package com.talalanguage.api.application.flashcards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.progress.RegisterLearningActivityUseCase;
import com.talalanguage.api.application.flashcards.exception.FlashcardNotFoundException;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.flashcards.Flashcard;
import com.talalanguage.api.domain.flashcards.FlashcardLanguage;
import com.talalanguage.api.domain.flashcards.ReviewRating;
import com.talalanguage.api.infrastructure.flashcards.InMemoryFlashcardRepository;
import com.talalanguage.api.infrastructure.flashcards.InMemoryFlashcardStatsRepository;
import com.talalanguage.api.infrastructure.flashcards.SimpleReviewSchedulePolicy;
import org.junit.jupiter.api.Test;

class ReviewFlashcardUseCaseTest {

    @Test
    void shouldReviewFlashcardAndRecalculateSchedule() {
        InMemoryFlashcardRepository flashcardRepository = new InMemoryFlashcardRepository();
        InMemoryFlashcardStatsRepository statsRepository = new InMemoryFlashcardStatsRepository();
        RegisterLearningActivityUseCase registerLearningActivityUseCase = new RegisterLearningActivityUseCase(
                new com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository()
        );
        ReviewFlashcardUseCase useCase = new ReviewFlashcardUseCase(
                flashcardRepository,
                new SimpleReviewSchedulePolicy(),
                statsRepository,
                registerLearningActivityUseCase
        );

        UserId userId = UserId.create();
        Flashcard flashcard = flashcardRepository.save(Flashcard.create(
                userId,
                "How are you?",
                "Como voce esta?",
                FlashcardLanguage.ENGLISH,
                java.util.List.of("greetings"),
                java.time.Instant.now()
        ));

        var result = useCase.execute(new ReviewFlashcardUseCase.Command(userId.value(), flashcard.id(), ReviewRating.GOOD));

        assertEquals(flashcard.id(), result.id());
        assertEquals(1, result.reviewCount());
    }

    @Test
    void shouldPreventReviewOfAnotherUsersFlashcard() {
        InMemoryFlashcardRepository flashcardRepository = new InMemoryFlashcardRepository();
        InMemoryFlashcardStatsRepository statsRepository = new InMemoryFlashcardStatsRepository();
        RegisterLearningActivityUseCase registerLearningActivityUseCase = new RegisterLearningActivityUseCase(
                new com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository()
        );
        ReviewFlashcardUseCase useCase = new ReviewFlashcardUseCase(
                flashcardRepository,
                new SimpleReviewSchedulePolicy(),
                statsRepository,
                registerLearningActivityUseCase
        );

        UserId ownerId = UserId.create();
        UserId intruderId = UserId.create();
        Flashcard flashcard = flashcardRepository.save(Flashcard.create(
                ownerId,
                "How are you?",
                "Como voce esta?",
                FlashcardLanguage.ENGLISH,
                java.util.List.of("greetings"),
                java.time.Instant.now()
        ));

        assertThrows(FlashcardNotFoundException.class, () ->
                useCase.execute(new ReviewFlashcardUseCase.Command(intruderId.value(), flashcard.id(), ReviewRating.GOOD))
        );
    }
}
