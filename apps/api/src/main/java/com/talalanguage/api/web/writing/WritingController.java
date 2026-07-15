package com.talalanguage.api.web.writing;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.writing.GetCurrentWritingChallengeUseCase;
import com.talalanguage.api.application.writing.ListRecentWritingSubmissionsUseCase;
import com.talalanguage.api.application.writing.ListWritingChallengesUseCase;
import com.talalanguage.api.application.writing.SubmitWritingAnswerUseCase;
import com.talalanguage.api.application.writing.exception.WritingChallengeNotFoundException;
import com.talalanguage.api.domain.writing.WritingLanguage;
import com.talalanguage.api.domain.writing.WritingLevel;
import com.talalanguage.api.web.writing.dto.CreateWritingSubmissionRequestDto;
import com.talalanguage.api.web.writing.dto.CreateWritingSubmissionResponseDto;
import com.talalanguage.api.web.writing.dto.RecentWritingSubmissionResponseDto;
import com.talalanguage.api.web.writing.dto.WritingChallengeResponseDto;
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
@RequestMapping("/api/writing")
public class WritingController {

    private final GetCurrentWritingChallengeUseCase getCurrentWritingChallengeUseCase;
    private final ListWritingChallengesUseCase listWritingChallengesUseCase;
    private final SubmitWritingAnswerUseCase submitWritingAnswerUseCase;
    private final ListRecentWritingSubmissionsUseCase listRecentWritingSubmissionsUseCase;

    public WritingController(
            GetCurrentWritingChallengeUseCase getCurrentWritingChallengeUseCase,
            ListWritingChallengesUseCase listWritingChallengesUseCase,
            SubmitWritingAnswerUseCase submitWritingAnswerUseCase,
            ListRecentWritingSubmissionsUseCase listRecentWritingSubmissionsUseCase
    ) {
        this.getCurrentWritingChallengeUseCase = getCurrentWritingChallengeUseCase;
        this.listWritingChallengesUseCase = listWritingChallengesUseCase;
        this.submitWritingAnswerUseCase = submitWritingAnswerUseCase;
        this.listRecentWritingSubmissionsUseCase = listRecentWritingSubmissionsUseCase;
    }

    @GetMapping("/challenges/current")
    public WritingChallengeResponseDto currentChallenge() {
        return WritingChallengeResponseDto.from(getCurrentWritingChallengeUseCase.execute());
    }

    @GetMapping("/challenges")
    public List<WritingChallengeResponseDto> challenges(
            @RequestParam Optional<String> language,
            @RequestParam Optional<String> level
    ) {
        return listWritingChallengesUseCase.execute(new ListWritingChallengesUseCase.Command(
                        language.map(WritingLanguage::valueOf),
                        level.map(WritingLevel::valueOf)
                ))
                .stream()
                .map(WritingChallengeResponseDto::from)
                .toList();
    }

    @PostMapping("/submissions")
    public ResponseEntity<CreateWritingSubmissionResponseDto> submit(
            Authentication authentication,
            @Valid @RequestBody CreateWritingSubmissionRequestDto request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateWritingSubmissionResponseDto.from(
                submitWritingAnswerUseCase.execute(new SubmitWritingAnswerUseCase.Command(
                        requireUserId(authentication),
                        request.challengeId(),
                        request.content()
                ))
        ));
    }

    @GetMapping("/submissions/recent")
    public List<RecentWritingSubmissionResponseDto> recent(Authentication authentication) {
        return listRecentWritingSubmissionsUseCase.execute(new ListRecentWritingSubmissionsUseCase.Command(
                        requireUserId(authentication)))
                .stream()
                .map(RecentWritingSubmissionResponseDto::from)
                .toList();
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        return authentication.getName();
    }
}
