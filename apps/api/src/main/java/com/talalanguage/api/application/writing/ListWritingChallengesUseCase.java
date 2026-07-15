package com.talalanguage.api.application.writing;

import com.talalanguage.api.application.writing.model.WritingChallengeView;
import com.talalanguage.api.application.writing.port.WritingChallengeRepository;
import com.talalanguage.api.domain.writing.WritingLanguage;
import com.talalanguage.api.domain.writing.WritingLevel;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ListWritingChallengesUseCase {

    private final WritingChallengeRepository writingChallengeRepository;

    public ListWritingChallengesUseCase(WritingChallengeRepository writingChallengeRepository) {
        this.writingChallengeRepository = writingChallengeRepository;
    }

    public List<WritingChallengeView> execute(Command command) {
        return writingChallengeRepository.listActive(command.language(), command.level())
                .stream()
                .map(WritingChallengeView::from)
                .toList();
    }

    public record Command(
            Optional<WritingLanguage> language,
            Optional<WritingLevel> level
    ) {
    }
}
