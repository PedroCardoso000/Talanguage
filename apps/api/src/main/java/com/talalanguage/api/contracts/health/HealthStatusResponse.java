package com.talalanguage.api.contracts.health;

import java.time.Instant;

public record HealthStatusResponse(
        String status,
        String service,
        Instant timestamp
) {
}
