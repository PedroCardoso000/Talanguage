package com.talalanguage.api.web.mocktest;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.mocktest.GetCurrentMockTestUseCase;
import com.talalanguage.api.application.mocktest.GetMockTestAttemptUseCase;
import com.talalanguage.api.application.mocktest.SubmitMockTestAttemptUseCase;
import com.talalanguage.api.web.mocktest.dto.CreateMockTestAttemptRequestDto;
import com.talalanguage.api.web.mocktest.dto.CurrentMockTestResponseDto;
import com.talalanguage.api.web.mocktest.dto.MockTestAttemptResponseDto;
import jakarta.validation.Valid;
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
@RequestMapping("/api/mock-tests")
public class MockTestsController {

    private final GetCurrentMockTestUseCase getCurrentMockTestUseCase;
    private final SubmitMockTestAttemptUseCase submitMockTestAttemptUseCase;
    private final GetMockTestAttemptUseCase getMockTestAttemptUseCase;

    public MockTestsController(
            GetCurrentMockTestUseCase getCurrentMockTestUseCase,
            SubmitMockTestAttemptUseCase submitMockTestAttemptUseCase,
            GetMockTestAttemptUseCase getMockTestAttemptUseCase
    ) {
        this.getCurrentMockTestUseCase = getCurrentMockTestUseCase;
        this.submitMockTestAttemptUseCase = submitMockTestAttemptUseCase;
        this.getMockTestAttemptUseCase = getMockTestAttemptUseCase;
    }

    @GetMapping("/current")
    public CurrentMockTestResponseDto current() {
        return CurrentMockTestResponseDto.from(getCurrentMockTestUseCase.execute());
    }

    @PostMapping("/attempts")
    public ResponseEntity<MockTestAttemptResponseDto> submit(
            Authentication authentication,
            @Valid @RequestBody CreateMockTestAttemptRequestDto request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(MockTestAttemptResponseDto.from(
                submitMockTestAttemptUseCase.execute(new SubmitMockTestAttemptUseCase.Command(
                        requireUserId(authentication),
                        request.mockTestId(),
                        request.answers().stream()
                                .map(answer -> new SubmitMockTestAttemptUseCase.AnswerCommand(
                                        answer.questionId(),
                                        answer.selectedOption()
                                ))
                                .toList()
                ))
        ));
    }

    @GetMapping("/attempts/{attemptId}")
    public MockTestAttemptResponseDto attempt(
            Authentication authentication,
            @PathVariable String attemptId
    ) {
        return MockTestAttemptResponseDto.from(getMockTestAttemptUseCase.execute(
                new GetMockTestAttemptUseCase.Command(requireUserId(authentication), attemptId)
        ));
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        return authentication.getName();
    }
}
