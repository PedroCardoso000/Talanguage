package com.talalanguage.api.infrastructure.community;

import com.talalanguage.api.application.community.port.PracticePartnerQueryService;
import com.talalanguage.api.domain.community.CommunityLanguage;
import com.talalanguage.api.domain.community.CommunityLevel;
import com.talalanguage.api.domain.community.PracticePartnerProfile;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class InMemoryPracticePartnerQueryService implements PracticePartnerQueryService {

    private static final List<PracticePartnerProfile> PARTNERS = List.of(
            new PracticePartnerProfile(
                    "partner-ana",
                    "Ana Torres",
                    List.of(CommunityLanguage.ENGLISH),
                    CommunityLevel.INTERMEDIATE,
                    "Disponivel a noite para conversas rapidas focadas em trabalho."
            ),
            new PracticePartnerProfile(
                    "partner-carlos",
                    "Carlos Vega",
                    List.of(CommunityLanguage.SPANISH, CommunityLanguage.ENGLISH),
                    CommunityLevel.BEGINNER,
                    "Quer praticar 20 minutos por dia com rotina objetiva."
            ),
            new PracticePartnerProfile(
                    "partner-luisa",
                    "Luisa Kim",
                    List.of(CommunityLanguage.ENGLISH),
                    CommunityLevel.ADVANCED,
                    "Gosta de praticar apresentacoes e feedback direto."
            )
    );

    @Override
    public List<PracticePartnerProfile> findSuggested(Optional<CommunityLanguage> language) {
        return PARTNERS.stream()
                .filter(partner -> language.map(value -> partner.languagesPracticed().contains(value)).orElse(true))
                .toList();
    }

    @Override
    public boolean existsById(String id) {
        return PARTNERS.stream().anyMatch(partner -> partner.userId().equals(id));
    }
}
