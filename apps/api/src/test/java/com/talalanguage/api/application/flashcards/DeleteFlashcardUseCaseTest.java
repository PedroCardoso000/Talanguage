package com.talalanguage.api.application.flashcards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.flashcards.exception.FlashcardNotFoundException;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.flashcards.Flashcard;
import com.talalanguage.api.domain.flashcards.FlashcardLanguage;
import com.talalanguage.api.infrastructure.flashcards.InMemoryFlashcardRepository;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;

class DeleteFlashcardUseCaseTest {

    @Test
    void shouldDeleteUsersFlashcard() {
        InMemoryFlashcardRepository repository = new InMemoryFlashcardRepository();
        DeleteFlashcardUseCase useCase = new DeleteFlashcardUseCase(repository);
        UserId userId = UserId.create();
        Flashcard flashcard = repository.save(Flashcard.create(
                userId,
                "Deadline",
                "Prazo final",
                FlashcardLanguage.ENGLISH,
                List.of("trabalho"),
                Instant.now()
        ));

        useCase.execute(new DeleteFlashcardUseCase.Command(userId.value(), flashcard.id()));

        assertEquals(0, repository.listByUserId(userId).size());
    }

    @Test
    void shouldRejectDeletingAnotherUsersFlashcard() {
        InMemoryFlashcardRepository repository = new InMemoryFlashcardRepository();
        DeleteFlashcardUseCase useCase = new DeleteFlashcardUseCase(repository);
        UserId ownerId = UserId.create();
        UserId intruderId = UserId.create();
        Flashcard flashcard = repository.save(Flashcard.create(
                ownerId,
                "Deadline",
                "Prazo final",
                FlashcardLanguage.ENGLISH,
                List.of("trabalho"),
                Instant.now()
        ));

        assertThrows(FlashcardNotFoundException.class, () ->
                useCase.execute(new DeleteFlashcardUseCase.Command(intruderId.value(), flashcard.id()))
        );
    }
}
