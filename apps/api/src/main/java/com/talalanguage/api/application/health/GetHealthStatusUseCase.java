package com.talalanguage.api.application.health;

import com.talalanguage.api.contracts.health.HealthStatusResponse;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class GetHealthStatusUseCase {

    public HealthStatusResponse execute() {
        return new HealthStatusResponse("ok", "talanguage-api", Instant.now());
    }
}
