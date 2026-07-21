package com.talalanguage.api.application.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.flashcards.port.SearchableFlashcardSource;
import com.talalanguage.api.application.search.port.SearchableModuleSource;
import com.talalanguage.api.domain.search.SearchResult;
import com.talalanguage.api.domain.search.SearchResultType;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

@ExtendWith(OutputCaptureExtension.class)
class GlobalSearchUseCaseTest {
    private static final SearchableModuleSource NO_MODULES = (query, limit) -> List.of();
    private static final SearchableFlashcardSource NO_FLASHCARDS = (userId, query, limit) -> List.of();

    @Test
    void shouldRejectInvalidTermsAndLimits() {
        GlobalSearchUseCase useCase = new GlobalSearchUseCase(NO_MODULES, NO_FLASHCARDS);

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(command(" ", null, 10)));
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(command("a", null, 10)));
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(command("x".repeat(101), null, 10)));
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(command("travel", null, 31)));
    }

    @Test
    void shouldFilterTypesAndRespectLimit() {
        SearchableModuleSource modules = (query, limit) -> List.of(result(SearchResultType.MODULE, "module", "Travel"));
        SearchableFlashcardSource flashcards = (userId, query, limit) -> List.of(
                result(SearchResultType.FLASHCARD, "one", "Travel one"),
                result(SearchResultType.FLASHCARD, "two", "Travel two")
        );
        GlobalSearchUseCase useCase = new GlobalSearchUseCase(modules, flashcards);

        var result = useCase.execute(command("travel", Set.of(SearchResultType.FLASHCARD), 1));

        assertEquals(1, result.results().size());
        assertEquals(SearchResultType.FLASHCARD, result.results().get(0).type());
    }

    @Test
    void shouldRankExactBeforePrefixAndPartial() {
        SearchableFlashcardSource flashcards = (userId, query, limit) -> List.of(
                SearchResult.matching(SearchResultType.FLASHCARD, "partial", "My travel notes", "", "/review", query),
                SearchResult.matching(SearchResultType.FLASHCARD, "exact", "Travel", "", "/review", query),
                SearchResult.matching(SearchResultType.FLASHCARD, "prefix", "Travel vocabulary", "", "/review", query)
        );
        GlobalSearchUseCase useCase = new GlobalSearchUseCase(NO_MODULES, flashcards);

        var results = useCase.execute(command("TrAvEl", Set.of(SearchResultType.FLASHCARD), 10)).results();

        assertEquals(List.of("exact", "prefix", "partial"), results.stream().map(SearchResult::id).toList());
    }

    @Test
    void shouldApplyLimitOnlyAfterGlobalDeterministicOrdering() {
        SearchableModuleSource modules = (query, sourceLimit) -> {
            assertEquals(GlobalSearchUseCase.MAX_LIMIT, sourceLimit);
            return List.of(new SearchResult(
                    SearchResultType.MODULE, "module", "Travel notes", "", "/review", 0.4));
        };
        SearchableFlashcardSource flashcards = (userId, query, sourceLimit) -> {
            assertEquals(GlobalSearchUseCase.MAX_LIMIT, sourceLimit);
            return List.of(result(SearchResultType.FLASHCARD, "flashcard", "Travel"));
        };
        GlobalSearchUseCase useCase = new GlobalSearchUseCase(modules, flashcards);

        var result = useCase.execute(command("travel", null, 1));

        assertEquals(List.of("flashcard"), result.results().stream().map(SearchResult::id).toList());
    }

    @Test
    void shouldApplyDefaultAndMaximumFinalLimits() {
        SearchableModuleSource modules = (query, sourceLimit) -> IntStream.range(0, 31)
                .mapToObj(index -> result(SearchResultType.MODULE, "module-" + index, "Travel"))
                .toList();
        GlobalSearchUseCase useCase = new GlobalSearchUseCase(modules, NO_FLASHCARDS);

        assertEquals(10, useCase.execute(command("travel", null, null)).results().size());
        assertEquals(30, useCase.execute(command("travel", null, 30)).results().size());
    }

    @Test
    void shouldBreakScoreTiesDeterministically() {
        SearchableModuleSource modules = (query, limit) -> List.of(
                result(SearchResultType.MODULE, "b", "Travel"),
                result(SearchResultType.MODULE, "a", "Travel")
        );
        GlobalSearchUseCase useCase = new GlobalSearchUseCase(modules, NO_FLASHCARDS);

        assertEquals(List.of("a", "b"), useCase.execute(command("travel", null, 10)).results()
                .stream().map(SearchResult::id).toList());
    }

    @Test
    void shouldContinueWhenOneSourceIsUnavailable() {
        SearchableModuleSource unavailable = (query, limit) -> {
            throw new SearchSourceUnavailableException("MODULE", new IllegalStateException("offline"));
        };
        SearchableFlashcardSource flashcards = (userId, query, limit) ->
                List.of(result(SearchResultType.FLASHCARD, "safe", "Travel"));
        GlobalSearchUseCase useCase = new GlobalSearchUseCase(unavailable, flashcards);

        var result = useCase.execute(command("travel", null, 10));

        assertEquals(List.of("safe"), result.results().stream().map(SearchResult::id).toList());
    }

    @Test
    void shouldNotHideUnexpectedErrors() {
        SearchableModuleSource broken = (query, limit) -> { throw new IllegalStateException("bug"); };
        GlobalSearchUseCase useCase = new GlobalSearchUseCase(broken, NO_FLASHCARDS);

        assertThrows(IllegalStateException.class, () -> useCase.execute(command("travel", null, 10)));
    }

    @Test
    void shouldNotHideAuthenticationOrAuthorizationErrors() {
        SearchableModuleSource unauthenticated = (query, limit) -> {
            throw new AuthenticationCredentialsNotFoundException("missing authentication");
        };
        SearchableModuleSource forbidden = (query, limit) -> {
            throw new AccessDeniedException("forbidden");
        };

        assertThrows(AuthenticationCredentialsNotFoundException.class, () ->
                new GlobalSearchUseCase(unauthenticated, NO_FLASHCARDS)
                        .execute(command("travel", null, 10)));
        assertThrows(AccessDeniedException.class, () ->
                new GlobalSearchUseCase(forbidden, NO_FLASHCARDS)
                        .execute(command("travel", null, 10)));
    }

    @Test
    void shouldNotLogQueryUserOrPrivateContentWhenSourceIsUnavailable(CapturedOutput output) {
        SearchableModuleSource unavailable = (query, limit) -> {
            throw new SearchSourceUnavailableException(
                    "MODULE", new IllegalStateException("private flashcard content"));
        };
        GlobalSearchUseCase useCase = new GlobalSearchUseCase(unavailable, NO_FLASHCARDS);

        useCase.execute(new GlobalSearchUseCase.Command(
                "owner@example.com", "private-query", null, 10));

        assertFalse(output.getAll().contains("private-query"));
        assertFalse(output.getAll().contains("owner@example.com"));
        assertFalse(output.getAll().contains("private flashcard content"));
    }

    private GlobalSearchUseCase.Command command(String query, Set<SearchResultType> types, Integer limit) {
        return new GlobalSearchUseCase.Command("user-one", query, types, limit);
    }

    private static SearchResult result(SearchResultType type, String id, String title) {
        return new SearchResult(type, id, title, "Safe description", "/review", 1.0);
    }
}
