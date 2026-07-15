package com.talalanguage.api.application.speaking;

import com.talalanguage.api.application.speaking.exception.SpeakingSessionAlreadyFinishedException;
import com.talalanguage.api.application.speaking.exception.SpeakingSessionNotFoundException;
import com.talalanguage.api.application.speaking.exception.SpeakingTopicNotFoundException;
import com.talalanguage.api.application.speaking.model.SubmittedSpeakingResponseView;
import com.talalanguage.api.application.speaking.port.SpeakingSessionRepository;
import com.talalanguage.api.application.speaking.port.SpeakingTopicRepository;
import com.talalanguage.api.domain.speaking.SpeakingSessionStatus;
import org.springframework.stereotype.Service;

@Service
public class SubmitSpeakingResponseUseCase {

    private final SpeakingSessionRepository speakingSessionRepository;
    private final SpeakingTopicRepository speakingTopicRepository;

    public SubmitSpeakingResponseUseCase(
            SpeakingSessionRepository speakingSessionRepository,
            SpeakingTopicRepository speakingTopicRepository
    ) {
        this.speakingSessionRepository = speakingSessionRepository;
        this.speakingTopicRepository = speakingTopicRepository;
    }

    public SubmittedSpeakingResponseView execute(Command command) {
        var session = speakingSessionRepository.findById(command.sessionId())
                .orElseThrow(() -> new SpeakingSessionNotFoundException(command.sessionId()));

        if (session.status() == SpeakingSessionStatus.FINISHED) {
            throw new SpeakingSessionAlreadyFinishedException(command.sessionId());
        }

        var topic = speakingTopicRepository.findById(session.topicId())
                .orElseThrow(() -> new SpeakingTopicNotFoundException(session.topicId()));

        var updatedSession = speakingSessionRepository.save(session.submitResponse(
                command.content().trim(),
                topic.followUpPrompt()
        ));

        return new SubmittedSpeakingResponseView(updatedSession.currentPrompt());
    }

    public record Command(String sessionId, String content) {
    }
}
