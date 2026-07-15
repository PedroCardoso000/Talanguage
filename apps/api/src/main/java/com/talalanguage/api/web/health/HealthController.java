package com.talalanguage.api.web.health;

import com.talalanguage.api.application.health.GetHealthStatusUseCase;
import com.talalanguage.api.contracts.health.HealthStatusResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    private final GetHealthStatusUseCase getHealthStatusUseCase;

    public HealthController(GetHealthStatusUseCase getHealthStatusUseCase) {
        this.getHealthStatusUseCase = getHealthStatusUseCase;
    }

    @GetMapping
    public HealthStatusResponse health() {
        return getHealthStatusUseCase.execute();
    }
}
