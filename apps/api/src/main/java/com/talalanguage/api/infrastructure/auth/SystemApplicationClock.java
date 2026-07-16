package com.talalanguage.api.infrastructure.auth;

import com.talalanguage.api.application.auth.port.ApplicationClock;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class SystemApplicationClock implements ApplicationClock {
    @Override
    public Instant now() { return Instant.now(); }
}
