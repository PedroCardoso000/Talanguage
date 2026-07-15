package com.talalanguage.api.application.speaking;

import com.talalanguage.api.application.speaking.exception.SpeakingTopicNotFoundException;
import com.talalanguage.api.application.speaking.model.StartedSpeakingSessionView;
import com.talalanguage.api.application.speaking.port.SpeakingSessionRepository;
import com.talalanguage.api.application.speaking.port.SpeakingTopicRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.speaking.SpeakingSession;
import org.springframework.stereotype.Service;

@Service
public class StartSpeakingSessionUseCase {

    private final SpeakingTopicRepository speakingTopicRepository;
    private final SpeakingSessionRepository speakingSessionRepository;

    public StartSpeakingSessionUseCase(
            SpeakingTopicRepository speakingTopicRepository,
            SpeakingSessionRepository speakingSessionRepository
    ) {
        this.speakingTopicRepository = speakingTopicRepository;
        this.speakingSessionRepository = speakingSessionRepository;
    }

    public StartedSpeakingSessionView execute(Command command) {
        var topic = speakingTopicRepository.findById(command.topicId())
                .orElseThrow(() -> new SpeakingTopicNotFoundException(command.topicId()));

        SpeakingSession session = speakingSessionRepository.save(SpeakingSession.start(
                UserId.from(command.userId()),
                command.language(),
                command.level(),
                topic
        ));

        return new StartedSpeakingSessionView(
                session.id(),
                session.status().name(),
                session.startedAt().toString(),
                session.currentPrompt()
        );
    }

    public record Command(
            String userId,
            com.talalanguage.api.domain.speaking.SpeakingLanguage language,
            com.talalanguage.api.domain.speaking.SpeakingLevel level,
            String topicId
    ) {
    }
}
