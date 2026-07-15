package com.talalanguage.api.web.progress;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.progress.GetLearningActivitiesUseCase;
import com.talalanguage.api.application.progress.GetProgressSummaryUseCase;
import com.talalanguage.api.application.progress.GetWeeklyProgressSummaryUseCase;
import com.talalanguage.api.web.progress.dto.ProgressActivityResponseDto;
import com.talalanguage.api.web.progress.dto.ProgressSummaryResponseDto;
import com.talalanguage.api.web.progress.dto.WeeklyProgressSummaryResponseDto;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final GetProgressSummaryUseCase getProgressSummaryUseCase;
    private final GetLearningActivitiesUseCase getLearningActivitiesUseCase;
    private final GetWeeklyProgressSummaryUseCase getWeeklyProgressSummaryUseCase;

    public ProgressController(
            GetProgressSummaryUseCase getProgressSummaryUseCase,
            GetLearningActivitiesUseCase getLearningActivitiesUseCase,
            GetWeeklyProgressSummaryUseCase getWeeklyProgressSummaryUseCase
    ) {
        this.getProgressSummaryUseCase = getProgressSummaryUseCase;
        this.getLearningActivitiesUseCase = getLearningActivitiesUseCase;
        this.getWeeklyProgressSummaryUseCase = getWeeklyProgressSummaryUseCase;
    }

    @GetMapping("/summary")
    public ProgressSummaryResponseDto summary(Authentication authentication) {
        return ProgressSummaryResponseDto.from(getProgressSummaryUseCase.execute(
                new GetProgressSummaryUseCase.Command(requireUserId(authentication))
        ));
    }

    @GetMapping("/activities")
    public List<ProgressActivityResponseDto> activities(Authentication authentication) {
        return getLearningActivitiesUseCase.execute(
                        new GetLearningActivitiesUseCase.Command(requireUserId(authentication)))
                .stream()
                .map(ProgressActivityResponseDto::from)
                .toList();
    }

    @GetMapping("/weekly-summary")
    public WeeklyProgressSummaryResponseDto weeklySummary(Authentication authentication) {
        return WeeklyProgressSummaryResponseDto.from(getWeeklyProgressSummaryUseCase.execute(
                new GetWeeklyProgressSummaryUseCase.Command(requireUserId(authentication))
        ));
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        return authentication.getName();
    }
}
