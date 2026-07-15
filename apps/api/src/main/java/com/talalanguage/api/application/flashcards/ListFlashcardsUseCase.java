package com.talalanguage.api.application.flashcards;

import com.talalanguage.api.application.flashcards.model.FlashcardView;
import com.talalanguage.api.application.flashcards.port.FlashcardRepository;
import com.talalanguage.api.domain.auth.UserId;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ListFlashcardsUseCase {

    private final FlashcardRepository flashcardRepository;

    public ListFlashcardsUseCase(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    public List<FlashcardView> execute(Command command) {
        return flashcardRepository.listByUserId(UserId.from(command.userId()))
                .stream()
                .map(FlashcardView::from)
                .toList();
    }

    public record Command(String userId) {
    }
}
