package com.talalanguage.api.infrastructure.speaking;

import com.talalanguage.api.application.speaking.port.SpeakingSessionRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.speaking.SpeakingFeedback;
import com.talalanguage.api.domain.speaking.SpeakingLanguage;
import com.talalanguage.api.domain.speaking.SpeakingLevel;
import com.talalanguage.api.domain.speaking.SpeakingSession;
import com.talalanguage.api.domain.speaking.SpeakingSessionStatus;
import com.talalanguage.api.infrastructure.persistence.entity.SpeakingSessionMetricEntity;
import com.talalanguage.api.infrastructure.persistence.repository.SpeakingSessionMetricJpaRepository;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaSpeakingSessionRepositoryAdapter implements SpeakingSessionRepository {

    private final SpeakingSessionMetricJpaRepository speakingSessionMetricJpaRepository;

    public JpaSpeakingSessionRepositoryAdapter(SpeakingSessionMetricJpaRepository speakingSessionMetricJpaRepository) {
        this.speakingSessionMetricJpaRepository = speakingSessionMetricJpaRepository;
    }

    @Override
    public SpeakingSession save(SpeakingSession session) {
        speakingSessionMetricJpaRepository.save(toEntity(session));
        return session;
    }

    @Override
    public Optional<SpeakingSession> findById(String id) {
        return speakingSessionMetricJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<SpeakingSession> listRecentFinishedByUserId(UserId userId, int limit) {
        return speakingSessionMetricJpaRepository.findTop5ByUserIdAndFinishedAtIsNotNullOrderByFinishedAtDesc(userId.value())
                .stream()
                .map(this::toDomain)
                .limit(limit)
                .toList();
    }

    private SpeakingSessionMetricEntity toEntity(SpeakingSession session) {
        Long durationSeconds = null;
        SpeakingFeedback feedback = session.feedbackSummary();

        if (session.startedAt() != null && session.finishedAt() != null) {
            durationSeconds = Duration.between(session.startedAt(), session.finishedAt()).getSeconds();
        }

        return new SpeakingSessionMetricEntity(
                session.id(),
                session.userId().value(),
                session.language().name(),
                session.level().name(),
                session.topicId(),
                session.status().name(),
                session.currentPrompt(),
                session.responseCount(),
                session.totalWordCount(),
                session.totalSentenceCount(),
                session.startedAt(),
                session.finishedAt(),
                durationSeconds,
                feedback == null ? null : feedback.score(),
                null,
                null,
                null,
                feedback == null ? null : feedback.feedback()
                ,
                feedback == null ? null : feedback.nextAction()
        );
    }

    private SpeakingSession toDomain(SpeakingSessionMetricEntity entity) {
        SpeakingFeedback feedback = entity.getScore() == null
                ? null
                : new SpeakingFeedback(
                        entity.getScore(),
                        entity.getFeedbackSummary(),
                        entity.getNextAction() == null
                                ? "Continue praticando com respostas mais completas no mesmo tema."
                                : entity.getNextAction()
                );

        return new SpeakingSession(
                entity.getId(),
                UserId.from(entity.getUserId()),
                SpeakingLanguage.valueOf(entity.getLanguage()),
                SpeakingLevel.valueOf(entity.getLevel()),
                entity.getTopicId(),
                SpeakingSessionStatus.valueOf(entity.getStatus()),
                entity.getCurrentPrompt(),
                List.of(),
                entity.getResponseCount(),
                entity.getTotalWordCount(),
                entity.getTotalSentenceCount(),
                entity.getStartedAt(),
                entity.getFinishedAt(),
                feedback
        );
    }
}
