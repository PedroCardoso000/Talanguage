package com.talalanguage.api.application.auth;

import com.talalanguage.api.application.auth.exception.EmailAlreadyInUseException;
import com.talalanguage.api.application.auth.exception.WeakPasswordException;
import com.talalanguage.api.application.auth.model.AuthSessionResult;
import com.talalanguage.api.application.notifications.CreateNotificationUseCase;
import com.talalanguage.api.application.auth.port.PasswordHasher;
import com.talalanguage.api.application.auth.port.SessionService;
import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.domain.auth.Email;
import com.talalanguage.api.domain.auth.User;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final SessionService sessionService;
    private final CreateNotificationUseCase createNotificationUseCase;

    public RegisterUserUseCase(
            UserRepository userRepository,
            PasswordHasher passwordHasher,
            SessionService sessionService,
            CreateNotificationUseCase createNotificationUseCase
    ) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.sessionService = sessionService;
        this.createNotificationUseCase = createNotificationUseCase;
    }

    public AuthSessionResult execute(Command command) {
        Email email = Email.of(command.email());
        validatePasswordStrength(command.password());

        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyInUseException();
        }

        User user = User.create(
                command.name(),
                email,
                passwordHasher.hash(command.password()),
                command.targetLanguage()
        );

        userRepository.save(user);
        createNotificationUseCase.execute(new CreateNotificationUseCase.Command(
                user.id().value(),
                "Bem-vindo ao Tala",
                "Seu treino comecou. Ajuste o perfil e mantenha consistencia diaria.",
                "/profile"
        ));
        return AuthSessionResult.from(user, sessionService.createSession(user.id()));
    }

    static void validatePasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            throw new WeakPasswordException();
        }

        boolean hasLetter = password.chars().anyMatch(Character::isLetter);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);

        if (!hasLetter || !hasDigit) {
            throw new WeakPasswordException();
        }
    }

    public record Command(
            String name,
            String email,
            String password,
            String targetLanguage
    ) {
    }
}
