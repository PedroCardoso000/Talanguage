package com.talalanguage.api.infrastructure.writing;

import com.talalanguage.api.application.writing.port.WritingSubmissionRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.writing.WritingFeedback;
import com.talalanguage.api.domain.writing.WritingLanguage;
import com.talalanguage.api.domain.writing.WritingLevel;
import com.talalanguage.api.domain.writing.WritingSubmission;
import com.talalanguage.api.domain.writing.WritingSubmissionStatus;
import com.talalanguage.api.infrastructure.persistence.PersistenceJsonCodec;
import com.talalanguage.api.infrastructure.persistence.entity.WritingSubmissionMetricEntity;
import com.talalanguage.api.infrastructure.persistence.repository.WritingSubmissionMetricJpaRepository;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaWritingSubmissionRepositoryAdapter implements WritingSubmissionRepository {

    private static final String REDACTED_CONTENT = "[content not retained]";

    private final WritingSubmissionMetricJpaRepository writingSubmissionMetricJpaRepository;
    private final PersistenceJsonCodec persistenceJsonCodec;

    public JpaWritingSubmissionRepositoryAdapter(
            WritingSubmissionMetricJpaRepository writingSubmissionMetricJpaRepository,
            PersistenceJsonCodec persistenceJsonCodec
    ) {
        this.writingSubmissionMetricJpaRepository = writingSubmissionMetricJpaRepository;
        this.persistenceJsonCodec = persistenceJsonCodec;
    }

    @Override
    public WritingSubmission save(WritingSubmission submission) {
        writingSubmissionMetricJpaRepository.save(new WritingSubmissionMetricEntity(
                submission.id(),
                submission.userId().value(),
                submission.challengeId(),
                submission.language().name(),
                submission.level().name(),
                submission.status().name(),
                submission.feedback().score(),
                persistenceJsonCodec.writeStringList(submission.feedback().strengths()),
                persistenceJsonCodec.writeStringList(submission.feedback().improvements()),
                submission.feedback().nextAction(),
                submission.submittedAt(),
                submission.reviewedAt()
        ));
        return submission;
    }

    @Override
    public List<WritingSubmission> listRecentByUserId(UserId userId, int limit) {
        return writingSubmissionMetricJpaRepository.findTop5ByUserIdOrderByReviewedAtDesc(userId.value())
                .stream()
                .map(this::toDomain)
                .limit(limit)
                .toList();
    }

    private WritingSubmission toDomain(WritingSubmissionMetricEntity entity) {
        return WritingSubmission.restore(
                entity.getId(),
                UserId.from(entity.getUserId()),
                entity.getChallengeId(),
                WritingLanguage.valueOf(entity.getLanguage()),
                WritingLevel.valueOf(entity.getLevel()),
                REDACTED_CONTENT,
                WritingSubmissionStatus.valueOf(entity.getStatus()),
                new WritingFeedback(
                        entity.getScore(),
                        persistenceJsonCodec.readStringList(entity.getStrengthsJson()),
                        persistenceJsonCodec.readStringList(entity.getImprovementsJson()),
                        entity.getNextAction()
                ),
                entity.getCreatedAt(),
                entity.getReviewedAt()
        );
    }
}
