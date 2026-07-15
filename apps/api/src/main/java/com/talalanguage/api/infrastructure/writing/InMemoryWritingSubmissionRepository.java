package com.talalanguage.api.infrastructure.writing;

import com.talalanguage.api.application.writing.port.WritingSubmissionRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.writing.WritingSubmission;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryWritingSubmissionRepository implements WritingSubmissionRepository {

    private final CopyOnWriteArrayList<WritingSubmission> submissions = new CopyOnWriteArrayList<>();

    @Override
    public WritingSubmission save(WritingSubmission submission) {
        submissions.add(submission);
        return submission;
    }

    @Override
    public List<WritingSubmission> listRecentByUserId(UserId userId, int limit) {
        return submissions.stream()
                .filter(submission -> submission.userId().equals(userId))
                .sorted(Comparator.comparing(WritingSubmission::submittedAt).reversed())
                .limit(limit)
                .toList();
    }

    public void clear() {
        submissions.clear();
    }
}
