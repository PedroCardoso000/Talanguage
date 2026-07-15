package com.talalanguage.api.web.dashboard;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.dashboard.GetDashboardSummaryUseCase;
import com.talalanguage.api.web.dashboard.dto.DashboardSummaryResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final GetDashboardSummaryUseCase getDashboardSummaryUseCase;

    public DashboardController(GetDashboardSummaryUseCase getDashboardSummaryUseCase) {
        this.getDashboardSummaryUseCase = getDashboardSummaryUseCase;
    }

    @GetMapping("/summary")
    public DashboardSummaryResponseDto summary(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        return DashboardSummaryResponseDto.from(
                getDashboardSummaryUseCase.execute(new GetDashboardSummaryUseCase.Command(authentication.getName()))
        );
    }
}
