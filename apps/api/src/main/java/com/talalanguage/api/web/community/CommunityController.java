package com.talalanguage.api.web.community;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.community.ListPracticeGroupsUseCase;
import com.talalanguage.api.application.community.ListPracticePartnersUseCase;
import com.talalanguage.api.application.community.RegisterCommunityInterestUseCase;
import com.talalanguage.api.domain.community.CommunityInterestTargetType;
import com.talalanguage.api.domain.community.CommunityLanguage;
import com.talalanguage.api.domain.community.CommunityLevel;
import com.talalanguage.api.web.community.dto.CommunityInterestResponseDto;
import com.talalanguage.api.web.community.dto.PracticeGroupResponseDto;
import com.talalanguage.api.web.community.dto.PracticePartnerResponseDto;
import com.talalanguage.api.web.community.dto.RegisterCommunityInterestRequestDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    private final ListPracticeGroupsUseCase listPracticeGroupsUseCase;
    private final ListPracticePartnersUseCase listPracticePartnersUseCase;
    private final RegisterCommunityInterestUseCase registerCommunityInterestUseCase;

    public CommunityController(
            ListPracticeGroupsUseCase listPracticeGroupsUseCase,
            ListPracticePartnersUseCase listPracticePartnersUseCase,
            RegisterCommunityInterestUseCase registerCommunityInterestUseCase
    ) {
        this.listPracticeGroupsUseCase = listPracticeGroupsUseCase;
        this.listPracticePartnersUseCase = listPracticePartnersUseCase;
        this.registerCommunityInterestUseCase = registerCommunityInterestUseCase;
    }

    @GetMapping("/groups")
    public List<PracticeGroupResponseDto> groups(
            Authentication authentication,
            @RequestParam Optional<String> language,
            @RequestParam Optional<String> level
    ) {
        requireUserId(authentication);
        return listPracticeGroupsUseCase.execute(new ListPracticeGroupsUseCase.Command(
                        language.map(CommunityLanguage::valueOf),
                        level.map(CommunityLevel::valueOf)
                ))
                .stream()
                .map(PracticeGroupResponseDto::from)
                .toList();
    }

    @GetMapping("/partners")
    public List<PracticePartnerResponseDto> partners(
            Authentication authentication,
            @RequestParam Optional<String> language
    ) {
        requireUserId(authentication);
        return listPracticePartnersUseCase.execute(new ListPracticePartnersUseCase.Command(
                        language.map(CommunityLanguage::valueOf)
                ))
                .stream()
                .map(PracticePartnerResponseDto::from)
                .toList();
    }

    @PostMapping("/interests")
    public ResponseEntity<CommunityInterestResponseDto> registerInterest(
            Authentication authentication,
            @Valid @RequestBody RegisterCommunityInterestRequestDto request
    ) {
        var result = registerCommunityInterestUseCase.execute(new RegisterCommunityInterestUseCase.Command(
                requireUserId(authentication),
                CommunityInterestTargetType.valueOf(request.targetType()),
                request.targetId()
        ));

        HttpStatus status = result.alreadyRegistered() ? HttpStatus.OK : HttpStatus.CREATED;
        return ResponseEntity.status(status).body(CommunityInterestResponseDto.from(result));
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        return authentication.getName();
    }
}
