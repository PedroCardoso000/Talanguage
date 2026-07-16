package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.LearnerOnboardingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearnerOnboardingJpaRepository extends JpaRepository<LearnerOnboardingEntity, String> { }
