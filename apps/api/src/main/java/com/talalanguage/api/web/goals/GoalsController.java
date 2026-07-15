package com.talalanguage.api.web.goals;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.goals.GetGoalSettingsUseCase;
import com.talalanguage.api.application.goals.UpdateGoalSettingsUseCase;
import com.talalanguage.api.web.goals.dto.GoalSettingsResponseDto;
import com.talalanguage.api.web.goals.dto.UpdateGoalSettingsRequestDto;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goals")
public class GoalsController {

    private final GetGoalSettingsUseCase getGoalSettingsUseCase;
    private final UpdateGoalSettingsUseCase updateGoalSettingsUseCase;

    public GoalsController(
            GetGoalSettingsUseCase getGoalSettingsUseCase,
            UpdateGoalSettingsUseCase updateGoalSettingsUseCase
    ) {
        this.getGoalSettingsUseCase = getGoalSettingsUseCase;
        this.updateGoalSettingsUseCase = updateGoalSettingsUseCase;
    }

    @GetMapping
    public GoalSettingsResponseDto getGoals(Authentication authentication) {
        return GoalSettingsResponseDto.from(getGoalSettingsUseCase.execute(
                new GetGoalSettingsUseCase.Command(requireUserId(authentication))
        ));
    }

    @PutMapping
    public GoalSettingsResponseDto updateGoals(
            Authentication authentication,
            @Valid @RequestBody UpdateGoalSettingsRequestDto request
    ) {
        return GoalSettingsResponseDto.from(updateGoalSettingsUseCase.execute(
                new UpdateGoalSettingsUseCase.Command(
                        requireUserId(authentication),
                        request.dailyMinutes(),
                        request.weeklyMinutes(),
                        request.wordsPerDay(),
                        request.challengesPerWeek()
                )
        ));
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        return authentication.getName();
    }
}
