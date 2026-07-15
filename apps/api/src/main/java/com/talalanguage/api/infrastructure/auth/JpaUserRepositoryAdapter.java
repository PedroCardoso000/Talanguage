package com.talalanguage.api.infrastructure.auth;

import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.domain.auth.Email;
import com.talalanguage.api.domain.auth.User;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.infrastructure.persistence.entity.UserEntity;
import com.talalanguage.api.infrastructure.persistence.repository.UserJpaRepository;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaUserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public JpaUserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return userJpaRepository.findByEmail(email.value()).map(this::toDomain);
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.value()).map(this::toDomain);
    }

    @Override
    public User save(User user) {
        userJpaRepository.save(toEntity(user));
        return user;
    }

    private UserEntity toEntity(User user) {
        return new UserEntity(
                user.id().value(),
                user.name(),
                user.email().value(),
                user.passwordHash(),
                user.targetLanguage(),
                user.currentLevel(),
                user.studyGoal(),
                user.avatarUrl(),
                user.createdAt(),
                user.updatedAt()
        );
    }

    private User toDomain(UserEntity entity) {
        return User.restore(
                UserId.from(entity.getId()),
                entity.getName(),
                Email.of(entity.getEmail()),
                entity.getPasswordHash(),
                entity.getTargetLanguage(),
                entity.getCurrentLevel(),
                entity.getStudyGoal(),
                entity.getAvatarUrl(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
