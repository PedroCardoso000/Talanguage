package com.talalanguage.api.infrastructure.onboarding;

import com.talalanguage.api.application.onboarding.port.LearnerOnboardingRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.onboarding.LearnerOnboarding;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryLearnerOnboardingRepository implements LearnerOnboardingRepository {
    private final Map<String, LearnerOnboarding> byUserId = new ConcurrentHashMap<>();
    @Override public Optional<LearnerOnboarding> findByUserId(UserId userId) { return Optional.ofNullable(byUserId.get(userId.value())); }
    @Override public LearnerOnboarding save(LearnerOnboarding value) { byUserId.put(value.userId().value(), value); return value; }
    @Override public boolean existsByUserId(UserId userId) { return byUserId.containsKey(userId.value()); }
    public int size() { return byUserId.size(); }
    public void clear() { byUserId.clear(); }
}
