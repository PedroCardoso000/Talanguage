package com.talalanguage.api.application.speaking.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.speaking.SpeakingSession;
import java.util.List;
import java.util.Optional;

public interface SpeakingSessionRepository {

    SpeakingSession save(SpeakingSession session);

    Optional<SpeakingSession> findById(String id);

    List<SpeakingSession> listRecentFinishedByUserId(UserId userId, int limit);
}
