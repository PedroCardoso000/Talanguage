package com.talalanguage.api.application.speaking;

import com.talalanguage.api.application.speaking.model.RecentSpeakingSessionView;
import com.talalanguage.api.application.speaking.port.SpeakingSessionRepository;
import com.talalanguage.api.application.speaking.port.SpeakingTopicRepository;
import com.talalanguage.api.domain.auth.UserId;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ListRecentSpeakingSessionsUseCase {

    private final SpeakingSessionRepository speakingSessionRepository;
    private final SpeakingTopicRepository speakingTopicRepository;

    public ListRecentSpeakingSessionsUseCase(
            SpeakingSessionRepository speakingSessionRepository,
            SpeakingTopicRepository speakingTopicRepository
    ) {
        this.speakingSessionRepository = speakingSessionRepository;
        this.speakingTopicRepository = speakingTopicRepository;
    }

    public List<RecentSpeakingSessionView> execute(Command command) {
        return speakingSessionRepository.listRecentFinishedByUserId(UserId.from(command.userId()), 5).stream()
                .map(session -> {
                    var topic = speakingTopicRepository.findById(session.topicId()).orElse(null);
                    long approximateDurationMinutes = 0;
                    if (session.startedAt() != null && session.finishedAt() != null) {
                        long seconds = java.time.Duration.between(session.startedAt(), session.finishedAt()).getSeconds();
                        approximateDurationMinutes = Math.max(1, Math.round(Math.max(0, seconds) / 60.0));
                    }

                    return new RecentSpeakingSessionView(
                            session.id(),
                            session.topicId(),
                            topic == null ? session.topicId() : topic.title(),
                            session.startedAt().toString(),
                            session.finishedAt().toString(),
                            session.responseCount(),
                            approximateDurationMinutes,
                            session.feedbackSummary().feedback(),
                            session.feedbackSummary().nextAction()
                    );
                })
                .toList();
    }

    public record Command(String userId) {
    }
}
