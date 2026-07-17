package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.PasswordResetTokenEntity;
import jakarta.persistence.LockModeType;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PasswordResetTokenJpaRepository extends JpaRepository<PasswordResetTokenEntity, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PasswordResetTokenEntity> findByTokenHash(String tokenHash);

    @Modifying
    @Query("update PasswordResetTokenEntity token set token.usedAt = :invalidatedAt "
            + "where token.userId = :userId and token.usedAt is null")
    int invalidateActiveByUserId(@Param("userId") String userId, @Param("invalidatedAt") Instant invalidatedAt);
}
