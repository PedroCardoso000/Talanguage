package com.talalanguage.api.application.flashcards;

import com.talalanguage.api.application.flashcards.exception.FlashcardNotFoundException;
import com.talalanguage.api.application.flashcards.port.FlashcardRepository;
import com.talalanguage.api.domain.auth.UserId;
import org.springframework.stereotype.Service;

@Service
public class DeleteFlashcardUseCase {

    private final FlashcardRepository flashcardRepository;

    public DeleteFlashcardUseCase(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    public void execute(Command command) {
        UserId userId = UserId.from(command.userId());
        if (flashcardRepository.findByIdAndUserId(command.flashcardId(), userId).isEmpty()) {
            throw new FlashcardNotFoundException();
        }

        flashcardRepository.deleteByIdAndUserId(command.flashcardId(), userId);
    }

    public record Command(
            String userId,
            String flashcardId
    ) {
    }
}
