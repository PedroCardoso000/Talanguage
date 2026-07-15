package com.talalanguage.api.web.speaking;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.speaking.FinishSpeakingSessionUseCase;
import com.talalanguage.api.application.speaking.ListRecentSpeakingSessionsUseCase;
import com.talalanguage.api.application.speaking.ListSpeakingTopicsUseCase;
import com.talalanguage.api.application.speaking.StartSpeakingSessionUseCase;
import com.talalanguage.api.application.speaking.SubmitSpeakingResponseUseCase;
import com.talalanguage.api.domain.speaking.SpeakingLanguage;
import com.talalanguage.api.domain.speaking.SpeakingLevel;
import com.talalanguage.api.web.speaking.dto.FinishSpeakingSessionResponseDto;
import com.talalanguage.api.web.speaking.dto.RecentSpeakingSessionResponseDto;
import com.talalanguage.api.web.speaking.dto.SpeakingTopicResponseDto;
import com.talalanguage.api.web.speaking.dto.StartSpeakingSessionRequestDto;
import com.talalanguage.api.web.speaking.dto.StartSpeakingSessionResponseDto;
import com.talalanguage.api.web.speaking.dto.SubmitSpeakingResponseRequestDto;
import com.talalanguage.api.web.speaking.dto.SubmitSpeakingResponseResponseDto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/speaking")
public class SpeakingController {

    private final ListSpeakingTopicsUseCase listSpeakingTopicsUseCase;
    private final StartSpeakingSessionUseCase startSpeakingSessionUseCase;
    private final SubmitSpeakingResponseUseCase submitSpeakingResponseUseCase;
    private final FinishSpeakingSessionUseCase finishSpeakingSessionUseCase;
    private final ListRecentSpeakingSessionsUseCase listRecentSpeakingSessionsUseCase;

    public SpeakingController(
            ListSpeakingTopicsUseCase listSpeakingTopicsUseCase,
            StartSpeakingSessionUseCase startSpeakingSessionUseCase,
            SubmitSpeakingResponseUseCase submitSpeakingResponseUseCase,
            FinishSpeakingSessionUseCase finishSpeakingSessionUseCase,
            ListRecentSpeakingSessionsUseCase listRecentSpeakingSessionsUseCase
    ) {
        this.listSpeakingTopicsUseCase = listSpeakingTopicsUseCase;
        this.startSpeakingSessionUseCase = startSpeakingSessionUseCase;
        this.submitSpeakingResponseUseCase = submitSpeakingResponseUseCase;
        this.finishSpeakingSessionUseCase = finishSpeakingSessionUseCase;
        this.listRecentSpeakingSessionsUseCase = listRecentSpeakingSessionsUseCase;
    }

    @GetMapping("/topics")
    public List<SpeakingTopicResponseDto> topics(Authentication authentication) {
        requireUserId(authentication);
        return listSpeakingTopicsUseCase.execute().stream()
                .map(SpeakingTopicResponseDto::from)
                .toList();
    }

    @PostMapping("/sessions")
    public ResponseEntity<StartSpeakingSessionResponseDto> start(
            Authentication authentication,
            @Valid @RequestBody StartSpeakingSessionRequestDto request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(StartSpeakingSessionResponseDto.from(
                startSpeakingSessionUseCase.execute(new StartSpeakingSessionUseCase.Command(
                        requireUserId(authentication),
                        SpeakingLanguage.valueOf(request.language()),
                        SpeakingLevel.valueOf(request.level()),
                        request.topicId()
                ))
        ));
    }

    @PostMapping("/sessions/{sessionId}/responses")
    public SubmitSpeakingResponseResponseDto submitResponse(
            Authentication authentication,
            @PathVariable String sessionId,
            @Valid @RequestBody SubmitSpeakingResponseRequestDto request
    ) {
        requireUserId(authentication);
        return SubmitSpeakingResponseResponseDto.from(submitSpeakingResponseUseCase.execute(
                new SubmitSpeakingResponseUseCase.Command(sessionId, request.content())
        ));
    }

    @PostMapping("/sessions/{sessionId}/finish")
    public FinishSpeakingSessionResponseDto finish(
            Authentication authentication,
            @PathVariable String sessionId
    ) {
        requireUserId(authentication);
        return FinishSpeakingSessionResponseDto.from(finishSpeakingSessionUseCase.execute(
                new FinishSpeakingSessionUseCase.Command(sessionId)
        ));
    }

    @GetMapping("/sessions/recent")
    public List<RecentSpeakingSessionResponseDto> recent(Authentication authentication) {
        return listRecentSpeakingSessionsUseCase.execute(new ListRecentSpeakingSessionsUseCase.Command(
                        requireUserId(authentication)))
                .stream()
                .map(RecentSpeakingSessionResponseDto::from)
                .toList();
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        return authentication.getName();
    }
}
