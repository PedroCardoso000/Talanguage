package com.talalanguage.api.application.flashcards;

import com.talalanguage.api.application.flashcards.model.FlashcardView;
import com.talalanguage.api.application.flashcards.port.FlashcardRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.flashcards.Flashcard;
import com.talalanguage.api.domain.flashcards.FlashcardLanguage;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CreateFlashcardUseCase {

    private final FlashcardRepository flashcardRepository;

    public CreateFlashcardUseCase(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    public FlashcardView execute(Command command) {
        Flashcard flashcard = Flashcard.create(
                UserId.from(command.userId()),
                command.front(),
                command.back(),
                command.language(),
                command.tags(),
                Instant.now().plus(1, ChronoUnit.DAYS)
        );

        return FlashcardView.from(flashcardRepository.save(flashcard));
    }

    public record Command(
            String userId,
            String front,
            String back,
            FlashcardLanguage language,
            List<String> tags
    ) {
    }
}
