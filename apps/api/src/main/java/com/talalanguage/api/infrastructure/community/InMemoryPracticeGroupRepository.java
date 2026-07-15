package com.talalanguage.api.infrastructure.community;

import com.talalanguage.api.application.community.port.PracticeGroupRepository;
import com.talalanguage.api.domain.community.CommunityLanguage;
import com.talalanguage.api.domain.community.CommunityLevel;
import com.talalanguage.api.domain.community.PracticeGroup;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPracticeGroupRepository implements PracticeGroupRepository {

    private static final List<PracticeGroup> GROUPS = List.of(
            new PracticeGroup(
                    "group-travel-beginners",
                    "Travel English Circle",
                    CommunityLanguage.ENGLISH,
                    CommunityLevel.BEGINNER,
                    "Grupo leve para praticar situacoes de viagem e destravar conversas simples.",
                    18,
                    true
            ),
            new PracticeGroup(
                    "group-career-speaking",
                    "Career Speaking Sprint",
                    CommunityLanguage.ENGLISH,
                    CommunityLevel.INTERMEDIATE,
                    "Espaco para treinar apresentacao profissional, entrevistas e reunioes.",
                    26,
                    true
            ),
            new PracticeGroup(
                    "group-spanish-routine",
                    "Spanish Daily Routine",
                    CommunityLanguage.SPANISH,
                    CommunityLevel.BEGINNER,
                    "Rotina curta para quem quer ganhar consistencia em espanhol sem enrolacao.",
                    14,
                    true
            )
    );

    @Override
    public List<PracticeGroup> findActive(Optional<CommunityLanguage> language, Optional<CommunityLevel> level) {
        return GROUPS.stream()
                .filter(PracticeGroup::active)
                .filter(group -> language.map(value -> group.language() == value).orElse(true))
                .filter(group -> level.map(value -> group.level() == value).orElse(true))
                .toList();
    }

    @Override
    public boolean existsById(String id) {
        return GROUPS.stream().anyMatch(group -> group.id().equals(id) && group.active());
    }
}
