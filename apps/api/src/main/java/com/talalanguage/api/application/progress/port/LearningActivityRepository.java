package com.talalanguage.api.application.progress.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.LearningActivity;
import java.time.Instant;
import java.util.List;

public interface LearningActivityRepository {

    LearningActivity save(LearningActivity activity);

    List<LearningActivity> findByUserId(UserId userId);

    List<LearningActivity> findByUserIdBetween(UserId userId, Instant startInclusive, Instant endInclusive);
}
