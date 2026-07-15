package com.talalanguage.api.application.community;

import com.talalanguage.api.application.community.exception.CommunityTargetNotFoundException;
import com.talalanguage.api.application.community.model.CommunityInterestView;
import com.talalanguage.api.application.community.port.CommunityInterestRepository;
import com.talalanguage.api.application.community.port.PracticeGroupRepository;
import com.talalanguage.api.application.community.port.PracticePartnerQueryService;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.community.CommunityInterest;
import com.talalanguage.api.domain.community.CommunityInterestTargetType;
import org.springframework.stereotype.Service;

@Service
public class RegisterCommunityInterestUseCase {

    private final CommunityInterestRepository communityInterestRepository;
    private final PracticeGroupRepository practiceGroupRepository;
    private final PracticePartnerQueryService practicePartnerQueryService;

    public RegisterCommunityInterestUseCase(
            CommunityInterestRepository communityInterestRepository,
            PracticeGroupRepository practiceGroupRepository,
            PracticePartnerQueryService practicePartnerQueryService
    ) {
        this.communityInterestRepository = communityInterestRepository;
        this.practiceGroupRepository = practiceGroupRepository;
        this.practicePartnerQueryService = practicePartnerQueryService;
    }

    public CommunityInterestView execute(Command command) {
        ensureTargetExists(command.targetType(), command.targetId());
        UserId userId = UserId.from(command.userId());

        var existing = communityInterestRepository.findByUserIdAndTarget(userId, command.targetType(), command.targetId());
        if (existing.isPresent()) {
            return toView(existing.get(), true);
        }

        CommunityInterest interest = communityInterestRepository.save(CommunityInterest.create(
                userId,
                command.targetType(),
                command.targetId()
        ));

        return toView(interest, false);
    }

    private void ensureTargetExists(CommunityInterestTargetType targetType, String targetId) {
        boolean exists = switch (targetType) {
            case GROUP -> practiceGroupRepository.existsById(targetId);
            case PARTNER -> practicePartnerQueryService.existsById(targetId);
        };

        if (!exists) {
            throw new CommunityTargetNotFoundException(targetType.name(), targetId);
        }
    }

    private CommunityInterestView toView(CommunityInterest interest, boolean alreadyRegistered) {
        return new CommunityInterestView(
                interest.id(),
                interest.targetType().name(),
                interest.targetId(),
                interest.createdAt().toString(),
                alreadyRegistered
        );
    }

    public record Command(String userId, CommunityInterestTargetType targetType, String targetId) {
    }
}
