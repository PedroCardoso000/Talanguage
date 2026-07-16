package com.talalanguage.api.application.auth.port;

import java.time.Instant;

public interface ApplicationClock {
    Instant now();
}
