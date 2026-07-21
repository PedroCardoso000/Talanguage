package com.talalanguage.api.application.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.auth.exception.EmailAlreadyInUseException;
import com.talalanguage.api.application.auth.exception.WeakPasswordException;
import com.talalanguage.api.application.notifications.CreateNotificationUseCase;
import com.talalanguage.api.application.notifications.port.NotificationRepository;
import com.talalanguage.api.application.auth.port.PasswordHasher;
import com.talalanguage.api.application.auth.port.SessionService;
import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.domain.auth.AuthenticatedSession;
import com.talalanguage.api.domain.auth.Email;
import com.talalanguage.api.domain.auth.User;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.notifications.Notification;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class RegisterUserUseCaseTest {

    @Test
    void shouldRegisterValidUser() {
        RegisterUserUseCase useCase = new RegisterUserUseCase(
                new FakeUserRepository(),
                new FakePasswordHasher(),
                new FakeSessionService(),
                new CreateNotificationUseCase(new FakeNotificationRepository())
        );

        var result = useCase.execute(new RegisterUserUseCase.Command(
                "Pedro Cardoso",
                "pedro@example.com",
                "StrongPassword123",
                "ENGLISH"
        ));

        assertNotNull(result.accessToken());
        assertEquals("Pedro Cardoso", result.user().name());
        assertEquals("pedro@example.com", result.user().email());
        assertEquals("ENGLISH", result.user().targetLanguage());
    }

    @Test
    void shouldRejectInvalidEmail() {
        RegisterUserUseCase useCase = new RegisterUserUseCase(
                new FakeUserRepository(),
                new FakePasswordHasher(),
                new FakeSessionService(),
                new CreateNotificationUseCase(new FakeNotificationRepository())
        );

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(new RegisterUserUseCase.Command(
                "Pedro Cardoso",
                "invalid-email",
                "StrongPassword123",
                "ENGLISH"
        )));
    }

    @Test
    void shouldRejectDuplicateEmail() {
        FakeUserRepository userRepository = new FakeUserRepository();
        userRepository.save(User.create("Pedro Cardoso", Email.of("pedro@example.com"), "hashed:StrongPassword123", "ENGLISH"));

        RegisterUserUseCase useCase = new RegisterUserUseCase(
                userRepository,
                new FakePasswordHasher(),
                new FakeSessionService(),
                new CreateNotificationUseCase(new FakeNotificationRepository())
        );

        assertThrows(EmailAlreadyInUseException.class, () -> useCase.execute(new RegisterUserUseCase.Command(
                "Pedro Cardoso",
                "pedro@example.com",
                "StrongPassword123",
                "ENGLISH"
        )));
    }

    @Test
    void shouldRejectWeakPassword() {
        RegisterUserUseCase useCase = new RegisterUserUseCase(
                new FakeUserRepository(),
                new FakePasswordHasher(),
                new FakeSessionService(),
                new CreateNotificationUseCase(new FakeNotificationRepository())
        );

        assertThrows(WeakPasswordException.class, () -> useCase.execute(new RegisterUserUseCase.Command(
                "Pedro Cardoso",
                "pedro@example.com",
                "weak",
                "ENGLISH"
        )));
    }

    private static final class FakeUserRepository implements UserRepository {

        private final Map<String, User> usersById = new HashMap<>();
        private final Map<String, String> userIdsByEmail = new HashMap<>();

        @Override
        public Optional<User> findByEmail(Email email) {
            String userId = userIdsByEmail.get(email.value());
            return userId == null ? Optional.empty() : Optional.ofNullable(usersById.get(userId));
        }

        @Override
        public Optional<User> findById(UserId userId) {
            return Optional.ofNullable(usersById.get(userId.value()));
        }

        @Override
        public User save(User user) {
            usersById.put(user.id().value(), user);
            userIdsByEmail.put(user.email().value(), user.id().value());
            return user;
        }
    }

    private static final class FakePasswordHasher implements PasswordHasher {

        @Override
        public String hash(String rawPassword) {
            return "hashed:" + rawPassword;
        }

        @Override
        public boolean matches(String rawPassword, String passwordHash) {
            return passwordHash.equals(hash(rawPassword));
        }
    }

    private static final class FakeSessionService implements SessionService {

        @Override
        public AuthenticatedSession createSession(UserId userId) {
            return new AuthenticatedSession("token-" + userId.value(), userId, Instant.now());
        }

        @Override
        public Optional<AuthenticatedSession> findByToken(String token) {
            return Optional.empty();
        }

        @Override
        public void invalidate(String token) {
        }

        @Override
        public void invalidateAll(UserId userId) {
        }
    }

    private static final class FakeNotificationRepository implements NotificationRepository {

        @Override
        public Notification save(Notification notification) {
            return notification;
        }

        @Override
        public java.util.List<Notification> findByUserId(UserId userId) {
            return java.util.List.of();
        }

        @Override
        public Optional<Notification> findByIdAndUserId(String id, UserId userId) {
            return Optional.empty();
        }
    }
}
