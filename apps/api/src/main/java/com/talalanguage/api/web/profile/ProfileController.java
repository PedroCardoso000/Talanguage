package com.talalanguage.api.web.profile;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.profile.GetProfileUseCase;
import com.talalanguage.api.application.profile.UpdateProfileUseCase;
import com.talalanguage.api.web.profile.dto.ProfileResponseDto;
import com.talalanguage.api.web.profile.dto.UpdateProfileRequestDto;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final GetProfileUseCase getProfileUseCase;
    private final UpdateProfileUseCase updateProfileUseCase;

    public ProfileController(GetProfileUseCase getProfileUseCase, UpdateProfileUseCase updateProfileUseCase) {
        this.getProfileUseCase = getProfileUseCase;
        this.updateProfileUseCase = updateProfileUseCase;
    }

    @GetMapping("/me")
    public ProfileResponseDto me(Authentication authentication) {
        return ProfileResponseDto.from(getProfileUseCase.execute(new GetProfileUseCase.Command(requireUserId(authentication))));
    }

    @PutMapping("/me")
    public ProfileResponseDto update(
            Authentication authentication,
            @Valid @RequestBody UpdateProfileRequestDto request
    ) {
        var result = updateProfileUseCase.execute(new UpdateProfileUseCase.Command(
                requireUserId(authentication),
                request.name(),
                request.targetLanguage(),
                request.currentLevel(),
                request.studyGoal(),
                request.avatarUrl()
        ));

        return ProfileResponseDto.from(result);
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        return authentication.getName();
    }
}
