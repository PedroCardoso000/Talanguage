package com.talalanguage.api.web.flashcards;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.flashcards.CreateFlashcardUseCase;
import com.talalanguage.api.application.flashcards.DeleteFlashcardUseCase;
import com.talalanguage.api.application.flashcards.GetFlashcardStatsUseCase;
import com.talalanguage.api.application.flashcards.ListFlashcardsUseCase;
import com.talalanguage.api.application.flashcards.ReviewFlashcardUseCase;
import com.talalanguage.api.domain.flashcards.FlashcardLanguage;
import com.talalanguage.api.domain.flashcards.ReviewRating;
import com.talalanguage.api.web.flashcards.dto.CreateFlashcardRequestDto;
import com.talalanguage.api.web.flashcards.dto.FlashcardResponseDto;
import com.talalanguage.api.web.flashcards.dto.FlashcardReviewResponseDto;
import com.talalanguage.api.web.flashcards.dto.FlashcardStatsResponseDto;
import com.talalanguage.api.web.flashcards.dto.ReviewFlashcardRequestDto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flashcards")
public class FlashcardsController {

    private final ListFlashcardsUseCase listFlashcardsUseCase;
    private final CreateFlashcardUseCase createFlashcardUseCase;
    private final DeleteFlashcardUseCase deleteFlashcardUseCase;
    private final ReviewFlashcardUseCase reviewFlashcardUseCase;
    private final GetFlashcardStatsUseCase getFlashcardStatsUseCase;

    public FlashcardsController(
            ListFlashcardsUseCase listFlashcardsUseCase,
            CreateFlashcardUseCase createFlashcardUseCase,
            DeleteFlashcardUseCase deleteFlashcardUseCase,
            ReviewFlashcardUseCase reviewFlashcardUseCase,
            GetFlashcardStatsUseCase getFlashcardStatsUseCase
    ) {
        this.listFlashcardsUseCase = listFlashcardsUseCase;
        this.createFlashcardUseCase = createFlashcardUseCase;
        this.deleteFlashcardUseCase = deleteFlashcardUseCase;
        this.reviewFlashcardUseCase = reviewFlashcardUseCase;
        this.getFlashcardStatsUseCase = getFlashcardStatsUseCase;
    }

    @GetMapping
    public List<FlashcardResponseDto> list(Authentication authentication) {
        return listFlashcardsUseCase.execute(new ListFlashcardsUseCase.Command(requireUserId(authentication)))
                .stream()
                .map(FlashcardResponseDto::from)
                .toList();
    }

    @PostMapping
    public ResponseEntity<FlashcardResponseDto> create(
            Authentication authentication,
            @Valid @RequestBody CreateFlashcardRequestDto request
    ) {
        var result = createFlashcardUseCase.execute(new CreateFlashcardUseCase.Command(
                requireUserId(authentication),
                request.front(),
                request.back(),
                FlashcardLanguage.valueOf(request.language()),
                request.tags()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(FlashcardResponseDto.from(result));
    }

    @PostMapping("/{id}/review")
    public FlashcardReviewResponseDto review(
            Authentication authentication,
            @PathVariable String id,
            @Valid @RequestBody ReviewFlashcardRequestDto request
    ) {
        return FlashcardReviewResponseDto.from(reviewFlashcardUseCase.execute(new ReviewFlashcardUseCase.Command(
                requireUserId(authentication),
                id,
                ReviewRating.valueOf(request.rating())
        )));
    }

    @GetMapping("/stats")
    public FlashcardStatsResponseDto stats(Authentication authentication) {
        return FlashcardStatsResponseDto.from(getFlashcardStatsUseCase.execute(
                new GetFlashcardStatsUseCase.Command(requireUserId(authentication))
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            Authentication authentication,
            @PathVariable String id
    ) {
        deleteFlashcardUseCase.execute(new DeleteFlashcardUseCase.Command(
                requireUserId(authentication),
                id
        ));
        return ResponseEntity.noContent().build();
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }

        return authentication.getName();
    }
}
