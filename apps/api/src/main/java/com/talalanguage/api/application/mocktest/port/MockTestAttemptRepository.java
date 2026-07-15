package com.talalanguage.api.application.mocktest.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.mocktest.MockTestAttempt;
import java.util.Optional;

public interface MockTestAttemptRepository {

    MockTestAttempt save(MockTestAttempt attempt);

    Optional<MockTestAttempt> findByIdAndUserId(String attemptId, UserId userId);
}
