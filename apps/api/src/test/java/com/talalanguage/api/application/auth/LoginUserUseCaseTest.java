package com.talalanguage.api.application.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.auth.exception.InvalidCredentialsException;
import com.talalanguage.api.application.auth.port.PasswordHasher;
import com.talalanguage.api.application.auth.port.SessionService;
import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.domain.auth.AuthenticatedSession;
import com.talalanguage.api.domain.auth.Email;
import com.talalanguage.api.domain.auth.User;
import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class LoginUserUseCaseTest {

    @Test
    void shouldAuthenticateValidCredentials() {
        FakeUserRepository userRepository = new FakeUserRepository();
        userRepository.save(User.create("Pedro Cardoso", Email.of("pedro@example.com"), "hashed:StrongPassword123", "ENGLISH"));

        LoginUserUseCase useCase = new LoginUserUseCase(
                userRepository,
                new FakePasswordHasher(),
                new FakeSessionService()
        );

        var result = useCase.execute(new LoginUserUseCase.Command("pedro@example.com", "StrongPassword123"));

        assertNotNull(result.accessToken());
        assertEquals("Pedro Cardoso", result.user().name());
    }

    @Test
    void shouldRejectUnknownUser() {
        LoginUserUseCase useCase = new LoginUserUseCase(
                new FakeUserRepository(),
                new FakePasswordHasher(),
                new FakeSessionService()
        );

        assertThrows(InvalidCredentialsException.class, () -> useCase.execute(
                new LoginUserUseCase.Command("pedro@example.com", "StrongPassword123")
        ));
    }

    @Test
    void shouldRejectWrongPassword() {
        FakeUserRepository userRepository = new FakeUserRepository();
        userRepository.save(User.create("Pedro Cardoso", Email.of("pedro@example.com"), "hashed:StrongPassword123", "ENGLISH"));

        LoginUserUseCase useCase = new LoginUserUseCase(
                userRepository,
                new FakePasswordHasher(),
                new FakeSessionService()
        );

        assertThrows(InvalidCredentialsException.class, () -> useCase.execute(
                new LoginUserUseCase.Command("pedro@example.com", "WrongPassword123")
        ));
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
}
