package com.talalanguage.api.infrastructure.auth;

import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.domain.auth.Email;
import com.talalanguage.api.domain.auth.User;
import com.talalanguage.api.domain.auth.UserId;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> usersById = new ConcurrentHashMap<>();
    private final Map<String, String> userIdsByEmail = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findByEmail(Email email) {
        String userId = userIdsByEmail.get(email.value());

        if (userId == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(usersById.get(userId));
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

    public void clear() {
        usersById.clear();
        userIdsByEmail.clear();
    }
}
