package com.talalanguage.api.application.writing;

import com.talalanguage.api.application.writing.model.WritingSubmissionView;
import com.talalanguage.api.application.writing.port.WritingSubmissionRepository;
import com.talalanguage.api.domain.auth.UserId;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ListRecentWritingSubmissionsUseCase {

    private final WritingSubmissionRepository writingSubmissionRepository;

    public ListRecentWritingSubmissionsUseCase(WritingSubmissionRepository writingSubmissionRepository) {
        this.writingSubmissionRepository = writingSubmissionRepository;
    }

    public List<WritingSubmissionView> execute(Command command) {
        return writingSubmissionRepository.listRecentByUserId(UserId.from(command.userId()), 5)
                .stream()
                .map(WritingSubmissionView::from)
                .toList();
    }

    public record Command(String userId) {
    }
}
