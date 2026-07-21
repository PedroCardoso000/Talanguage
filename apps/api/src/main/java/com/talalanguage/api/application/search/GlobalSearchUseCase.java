package com.talalanguage.api.application.search;

import com.talalanguage.api.application.flashcards.port.SearchableFlashcardSource;
import com.talalanguage.api.application.search.port.SearchableModuleSource;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.search.SearchResult;
import com.talalanguage.api.domain.search.SearchResultType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class GlobalSearchUseCase {
    public static final int DEFAULT_LIMIT = 10;
    public static final int MAX_LIMIT = 30;

    private static final Comparator<SearchResult> RESULT_ORDER = Comparator
            .comparingDouble(SearchResult::score).reversed()
            .thenComparing(SearchResult::type)
            .thenComparing(result -> result.title().toLowerCase(Locale.ROOT))
            .thenComparing(SearchResult::id);

    private final SearchableModuleSource moduleSource;
    private final SearchableFlashcardSource flashcardSource;

    public GlobalSearchUseCase(SearchableModuleSource moduleSource, SearchableFlashcardSource flashcardSource) {
        this.moduleSource = moduleSource;
        this.flashcardSource = flashcardSource;
    }

    public Result execute(Command command) {
        if (command == null) {
            throw new IllegalArgumentException("Search command is required.");
        }
        String query = normalizeQuery(command.query());
        int limit = validateLimit(command.limit());
        Set<SearchResultType> types = command.types() == null || command.types().isEmpty()
                ? EnumSet.allOf(SearchResultType.class)
                : EnumSet.copyOf(command.types());
        UserId userId = UserId.from(command.userId());

        List<SearchResult> results = new ArrayList<>();
        if (types.contains(SearchResultType.MODULE)) {
            addAvailable(results, () -> moduleSource.search(query, limit));
        }
        if (types.contains(SearchResultType.FLASHCARD)) {
            addAvailable(results, () -> flashcardSource.search(userId, query, limit));
        }

        return new Result(query, results.stream().sorted(RESULT_ORDER).limit(limit).toList());
    }

    private void addAvailable(List<SearchResult> target, SearchOperation operation) {
        try {
            List<SearchResult> sourceResults = operation.search();
            if (sourceResults != null) {
                target.addAll(sourceResults);
            }
        } catch (RuntimeException unavailableSource) {
            // One optional search source must not make the complete search unavailable.
        }
    }

    private String normalizeQuery(String query) {
        if (query == null) {
            throw new IllegalArgumentException("Search query is required.");
        }
        String normalized = query.trim();
        if (normalized.length() < 2 || normalized.length() > 100) {
            throw new IllegalArgumentException("Search query must contain between 2 and 100 characters.");
        }
        return normalized;
    }

    private int validateLimit(Integer limit) {
        int resolved = limit == null ? DEFAULT_LIMIT : limit;
        if (resolved < 1 || resolved > MAX_LIMIT) {
            throw new IllegalArgumentException("Search limit must be between 1 and 30.");
        }
        return resolved;
    }

    @FunctionalInterface
    private interface SearchOperation {
        List<SearchResult> search();
    }

    public record Command(String userId, String query, Set<SearchResultType> types, Integer limit) {
    }

    public record Result(String query, List<SearchResult> results) {
    }
}
