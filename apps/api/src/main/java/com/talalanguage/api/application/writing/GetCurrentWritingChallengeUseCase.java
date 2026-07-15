package com.talalanguage.api.application.writing;

import com.talalanguage.api.application.writing.model.WritingChallengeView;
import com.talalanguage.api.application.writing.port.WritingChallengeRepository;
import org.springframework.stereotype.Service;

@Service
public class GetCurrentWritingChallengeUseCase {

    private final WritingChallengeRepository writingChallengeRepository;

    public GetCurrentWritingChallengeUseCase(WritingChallengeRepository writingChallengeRepository) {
        this.writingChallengeRepository = writingChallengeRepository;
    }

    public WritingChallengeView execute() {
        return writingChallengeRepository.listActive(java.util.Optional.empty(), java.util.Optional.empty())
                .stream()
                .findFirst()
                .map(WritingChallengeView::from)
                .orElse(null);
    }
}
