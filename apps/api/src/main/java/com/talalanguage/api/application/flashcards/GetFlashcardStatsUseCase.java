package com.talalanguage.api.application.flashcards;

import com.talalanguage.api.application.flashcards.model.FlashcardStatsView;
import com.talalanguage.api.application.flashcards.port.FlashcardStatsRepository;
import com.talalanguage.api.domain.auth.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetFlashcardStatsUseCase {

    private final FlashcardStatsRepository flashcardStatsRepository;

    public GetFlashcardStatsUseCase(FlashcardStatsRepository flashcardStatsRepository) {
        this.flashcardStatsRepository = flashcardStatsRepository;
    }

    public FlashcardStatsView execute(Command command) {
        return flashcardStatsRepository.getByUserId(UserId.from(command.userId()));
    }

    public record Command(String userId) {
    }
}
