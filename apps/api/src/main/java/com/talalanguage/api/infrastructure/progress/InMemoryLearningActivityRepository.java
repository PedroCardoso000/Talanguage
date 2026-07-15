package com.talalanguage.api.infrastructure.progress;

import com.talalanguage.api.application.progress.port.LearningActivityRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.LearningActivity;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryLearningActivityRepository implements LearningActivityRepository {

    private final CopyOnWriteArrayList<LearningActivity> activities = new CopyOnWriteArrayList<>();

    @Override
    public LearningActivity save(LearningActivity activity) {
        activities.add(activity);
        return activity;
    }

    @Override
    public List<LearningActivity> findByUserId(UserId userId) {
        return activities.stream()
                .filter(activity -> activity.userId().equals(userId))
                .sorted(Comparator.comparing(LearningActivity::completedAt))
                .toList();
    }

    @Override
    public List<LearningActivity> findByUserIdBetween(UserId userId, Instant startInclusive, Instant endInclusive) {
        return activities.stream()
                .filter(activity -> activity.userId().equals(userId))
                .filter(activity -> !activity.completedAt().isBefore(startInclusive) && !activity.completedAt().isAfter(endInclusive))
                .sorted(Comparator.comparing(LearningActivity::completedAt))
                .toList();
    }

    public void clear() {
        activities.clear();
    }
}
