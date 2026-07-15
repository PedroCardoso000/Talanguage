package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionJpaRepository extends JpaRepository<UserSessionEntity, String> {
}
