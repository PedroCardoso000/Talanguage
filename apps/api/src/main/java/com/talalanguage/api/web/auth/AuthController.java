package com.talalanguage.api.web.auth;

import com.talalanguage.api.application.auth.GetAuthenticatedUserUseCase;
import com.talalanguage.api.application.auth.LoginUserUseCase;
import com.talalanguage.api.application.auth.LogoutUserUseCase;
import com.talalanguage.api.application.auth.RegisterUserUseCase;
import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.web.auth.dto.AuthSessionResponseDto;
import com.talalanguage.api.web.auth.dto.AuthUserResponseDto;
import com.talalanguage.api.web.auth.dto.LoginRequestDto;
import com.talalanguage.api.web.auth.dto.RegisterRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;
    private final LogoutUserUseCase logoutUserUseCase;

    public AuthController(
            RegisterUserUseCase registerUserUseCase,
            LoginUserUseCase loginUserUseCase,
            GetAuthenticatedUserUseCase getAuthenticatedUserUseCase,
            LogoutUserUseCase logoutUserUseCase
    ) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
        this.getAuthenticatedUserUseCase = getAuthenticatedUserUseCase;
        this.logoutUserUseCase = logoutUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthSessionResponseDto> register(@Valid @RequestBody RegisterRequestDto request) {
        var result = registerUserUseCase.execute(new RegisterUserUseCase.Command(
                request.name(),
                request.email(),
                request.password(),
                request.targetLanguage()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(AuthSessionResponseDto.from(result));
    }

    @PostMapping("/login")
    public AuthSessionResponseDto login(@Valid @RequestBody LoginRequestDto request) {
        var result = loginUserUseCase.execute(new LoginUserUseCase.Command(
                request.email(),
                request.password()
        ));

        return AuthSessionResponseDto.from(result);
    }

    @GetMapping("/me")
    public AuthUserResponseDto me(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        var result = getAuthenticatedUserUseCase.execute(new GetAuthenticatedUserUseCase.Command(authentication.getName()));
        return AuthUserResponseDto.from(result);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(Authentication authentication) {
        if (authentication == null || authentication.getCredentials() == null) {
            throw new AuthenticationRequiredException();
        }

        logoutUserUseCase.execute(new LogoutUserUseCase.Command(authentication.getCredentials().toString()));
    }
}
