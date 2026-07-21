package com.talalanguage.api.application.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.flashcards.port.SearchableFlashcardSource;
import com.talalanguage.api.application.search.port.SearchableModuleSource;
import com.talalanguage.api.domain.search.SearchResult;
import com.talalanguage.api.domain.search.SearchResultType;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

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
        SearchableModuleSource modules = (query, sourceLimit) ->
                List.of(result(SearchResultType.MODULE, "module", "Travel"));
        SearchableFlashcardSource flashcards = (userId, query, sourceLimit) ->
                List.of(result(SearchResultType.FLASHCARD, "flashcard", "Travel"));
        GlobalSearchUseCase useCase = new GlobalSearchUseCase(modules, flashcards);

        var result = useCase.execute(command("travel", null, 1));

        assertEquals(List.of("module"), result.results().stream().map(SearchResult::id).toList());
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

    private GlobalSearchUseCase.Command command(String query, Set<SearchResultType> types, Integer limit) {
        return new GlobalSearchUseCase.Command("user-one", query, types, limit);
    }

    private static SearchResult result(SearchResultType type, String id, String title) {
        return new SearchResult(type, id, title, "Safe description", "/review", 1.0);
    }
}
