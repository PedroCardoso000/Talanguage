package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.GoalSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalSettingsJpaRepository extends JpaRepository<GoalSettingsEntity, String> {
}
