package com.talalanguage.api.application.auth.port;

import com.talalanguage.api.domain.auth.Email;
import com.talalanguage.api.domain.auth.User;
import com.talalanguage.api.domain.auth.UserId;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(Email email);

    Optional<User> findById(UserId userId);

    User save(User user);
}
