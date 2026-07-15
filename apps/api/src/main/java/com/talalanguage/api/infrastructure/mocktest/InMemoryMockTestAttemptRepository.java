package com.talalanguage.api.infrastructure.mocktest;

import com.talalanguage.api.application.mocktest.port.MockTestAttemptRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.mocktest.MockTestAttempt;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryMockTestAttemptRepository implements MockTestAttemptRepository {

    private final Map<String, MockTestAttempt> attemptsById = new ConcurrentHashMap<>();

    @Override
    public MockTestAttempt save(MockTestAttempt attempt) {
        attemptsById.put(attempt.id(), attempt);
        return attempt;
    }

    @Override
    public Optional<MockTestAttempt> findByIdAndUserId(String attemptId, UserId userId) {
        MockTestAttempt attempt = attemptsById.get(attemptId);
        if (attempt == null || !attempt.userId().equals(userId)) {
            return Optional.empty();
        }

        return Optional.of(attempt);
    }

    public void clear() {
        attemptsById.clear();
    }
}
