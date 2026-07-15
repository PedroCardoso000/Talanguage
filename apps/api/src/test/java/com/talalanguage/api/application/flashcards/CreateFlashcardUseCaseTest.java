package com.talalanguage.api.application.flashcards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.domain.flashcards.FlashcardLanguage;
import com.talalanguage.api.infrastructure.flashcards.InMemoryFlashcardRepository;
import java.util.List;
import org.junit.jupiter.api.Test;

class CreateFlashcardUseCaseTest {

    @Test
    void shouldCreateFlashcard() {
        CreateFlashcardUseCase useCase = new CreateFlashcardUseCase(new InMemoryFlashcardRepository());

        var result = useCase.execute(new CreateFlashcardUseCase.Command(
                "user-1",
                "How are you?",
                "Como voce esta?",
                FlashcardLanguage.ENGLISH,
                List.of("greetings")
        ));

        assertEquals("How are you?", result.front());
        assertEquals("Como voce esta?", result.back());
    }

    @Test
    void shouldRejectBlankFront() {
        CreateFlashcardUseCase useCase = new CreateFlashcardUseCase(new InMemoryFlashcardRepository());

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(new CreateFlashcardUseCase.Command(
                "user-1",
                " ",
                "Como voce esta?",
                FlashcardLanguage.ENGLISH,
                List.of("greetings")
        )));
    }

    @Test
    void shouldRejectBlankBack() {
        CreateFlashcardUseCase useCase = new CreateFlashcardUseCase(new InMemoryFlashcardRepository());

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(new CreateFlashcardUseCase.Command(
                "user-1",
                "How are you?",
                " ",
                FlashcardLanguage.ENGLISH,
                List.of("greetings")
        )));
    }
}
