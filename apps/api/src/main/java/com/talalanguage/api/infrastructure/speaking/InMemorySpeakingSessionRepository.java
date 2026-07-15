package com.talalanguage.api.infrastructure.speaking;

import com.talalanguage.api.application.speaking.port.SpeakingSessionRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.speaking.SpeakingSession;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemorySpeakingSessionRepository implements SpeakingSessionRepository {

    private final CopyOnWriteArrayList<SpeakingSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public SpeakingSession save(SpeakingSession session) {
        sessions.removeIf(existing -> existing.id().equals(session.id()));
        sessions.add(session);
        return session;
    }

    @Override
    public java.util.Optional<SpeakingSession> findById(String id) {
        return sessions.stream()
                .filter(session -> session.id().equals(id))
                .findFirst();
    }

    @Override
    public List<SpeakingSession> listRecentFinishedByUserId(UserId userId, int limit) {
        return sessions.stream()
                .filter(session -> session.userId().equals(userId))
                .filter(session -> session.finishedAt() != null && session.feedbackSummary() != null)
                .sorted(Comparator.comparing(SpeakingSession::finishedAt).reversed())
                .limit(limit)
                .toList();
    }

    public void clear() {
        sessions.clear();
    }
}
