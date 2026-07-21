package com.talalanguage.api.domain.progress;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

public final class LearningDayPolicy {

    public static final ZoneId ZONE_ID = ZoneOffset.UTC;

    private LearningDayPolicy() {
    }

    public static LocalDate today(Clock clock) {
        return LocalDate.now(clock.withZone(ZONE_ID));
    }

    public static LocalDate dateOf(Instant instant) {
        return instant.atZone(ZONE_ID).toLocalDate();
    }
}
