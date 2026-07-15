package com.talalanguage.api.application.speaking;

import com.talalanguage.api.application.progress.RegisterLearningActivityUseCase;
import com.talalanguage.api.application.speaking.exception.SpeakingSessionAlreadyFinishedException;
import com.talalanguage.api.application.speaking.exception.SpeakingSessionNotFoundException;
import com.talalanguage.api.application.speaking.exception.SpeakingTopicNotFoundException;
import com.talalanguage.api.application.speaking.model.FinishedSpeakingSessionView;
import com.talalanguage.api.application.speaking.model.SpeakingSummaryView;
import com.talalanguage.api.application.speaking.port.SpeakingFeedbackProvider;
import com.talalanguage.api.application.speaking.port.SpeakingSessionRepository;
import com.talalanguage.api.application.speaking.port.SpeakingTopicRepository;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.domain.speaking.SpeakingSessionStatus;
import java.time.Duration;
import org.springframework.stereotype.Service;

@Service
public class FinishSpeakingSessionUseCase {

    private final SpeakingSessionRepository speakingSessionRepository;
    private final SpeakingTopicRepository speakingTopicRepository;
    private final SpeakingFeedbackProvider speakingFeedbackProvider;
    private final RegisterLearningActivityUseCase registerLearningActivityUseCase;

    public FinishSpeakingSessionUseCase(
            SpeakingSessionRepository speakingSessionRepository,
            SpeakingTopicRepository speakingTopicRepository,
            SpeakingFeedbackProvider speakingFeedbackProvider,
            RegisterLearningActivityUseCase registerLearningActivityUseCase
    ) {
        this.speakingSessionRepository = speakingSessionRepository;
        this.speakingTopicRepository = speakingTopicRepository;
        this.speakingFeedbackProvider = speakingFeedbackProvider;
        this.registerLearningActivityUseCase = registerLearningActivityUseCase;
    }

    public FinishedSpeakingSessionView execute(Command command) {
        var session = speakingSessionRepository.findById(command.sessionId())
                .orElseThrow(() -> new SpeakingSessionNotFoundException(command.sessionId()));

        if (session.status() == SpeakingSessionStatus.FINISHED) {
            throw new SpeakingSessionAlreadyFinishedException(command.sessionId());
        }

        var topic = speakingTopicRepository.findById(session.topicId())
                .orElseThrow(() -> new SpeakingTopicNotFoundException(session.topicId()));
        var feedback = speakingFeedbackProvider.evaluate(session, topic);
        var finishedSession = speakingSessionRepository.save(session.finish(feedback));

        registerLearningActivityUseCase.execute(new RegisterLearningActivityUseCase.Command(
                finishedSession.userId().value(),
                ActivityType.SPEAKING,
                SkillType.SPEAKING,
                feedback.score(),
                finishedSession.finishedAt(),
                finishedSession.id()
        ));

        return new FinishedSpeakingSessionView(
                finishedSession.id(),
                finishedSession.status().name(),
                new SpeakingSummaryView(
                        finishedSession.responseCount(),
                        calculateApproximateDurationMinutes(finishedSession.startedAt(), finishedSession.finishedAt()),
                        topic.title(),
                        feedback.feedback(),
                        feedback.nextAction()
                )
        );
    }

    private long calculateApproximateDurationMinutes(java.time.Instant startedAt, java.time.Instant finishedAt) {
        if (startedAt == null || finishedAt == null) {
            return 0;
        }

        long seconds = Math.max(0, Duration.between(startedAt, finishedAt).getSeconds());
        return Math.max(1, Math.round(seconds / 60.0));
    }

    public record Command(String sessionId) {
    }
}
