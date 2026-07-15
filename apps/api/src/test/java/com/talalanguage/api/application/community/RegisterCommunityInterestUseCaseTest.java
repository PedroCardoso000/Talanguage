package com.talalanguage.api.application.community;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.talalanguage.api.domain.community.CommunityInterestTargetType;
import com.talalanguage.api.domain.community.CommunityLanguage;
import com.talalanguage.api.infrastructure.community.InMemoryCommunityInterestRepository;
import com.talalanguage.api.infrastructure.community.InMemoryPracticeGroupRepository;
import com.talalanguage.api.infrastructure.community.InMemoryPracticePartnerQueryService;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class RegisterCommunityInterestUseCaseTest {

    @Test
    void shouldRegisterInterestAndAvoidDuplicate() {
        var repository = new InMemoryCommunityInterestRepository();
        var useCase = new RegisterCommunityInterestUseCase(
                repository,
                new InMemoryPracticeGroupRepository(),
                new InMemoryPracticePartnerQueryService()
        );

        var first = useCase.execute(new RegisterCommunityInterestUseCase.Command(
                "user-1",
                CommunityInterestTargetType.GROUP,
                "group-travel-beginners"
        ));

        var duplicate = useCase.execute(new RegisterCommunityInterestUseCase.Command(
                "user-1",
                CommunityInterestTargetType.GROUP,
                "group-travel-beginners"
        ));

        assertFalse(first.alreadyRegistered());
        assertTrue(duplicate.alreadyRegistered());
        assertEquals(first.id(), duplicate.id());
    }

    @Test
    void shouldListPartnersByLanguage() {
        var useCase = new ListPracticePartnersUseCase(new InMemoryPracticePartnerQueryService());

        var result = useCase.execute(new ListPracticePartnersUseCase.Command(Optional.of(CommunityLanguage.SPANISH)));

        assertEquals(1, result.size());
        assertEquals("partner-carlos", result.get(0).userId());
    }
}
