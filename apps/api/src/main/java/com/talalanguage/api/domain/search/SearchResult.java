package com.talalanguage.api.domain.search;

import java.util.Locale;
import java.util.Objects;

public record SearchResult(
        SearchResultType type,
        String id,
        String title,
        String description,
        String route,
        double score
) {
    public SearchResult {
        Objects.requireNonNull(type, "type is required");
        id = requireText(id, "id");
        title = requireText(title, "title");
        description = description == null ? "" : description.trim();
        route = requireText(route, "route");
        if (!route.startsWith("/")) {
            throw new IllegalArgumentException("route must be application-relative");
        }
        if (score <= 0 || score > 1) {
            throw new IllegalArgumentException("score must be between 0 and 1");
        }
    }

    public static SearchResult matching(
            SearchResultType type,
            String id,
            String title,
            String description,
            String route,
            String query
    ) {
        double score = score(title, description, query);
        return score == 0 ? null : new SearchResult(type, id, title, description, route, score);
    }

    private static double score(String title, String description, String query) {
        String normalizedQuery = normalize(query);
        String normalizedTitle = normalize(title);
        String normalizedDescription = normalize(description);
        if (normalizedTitle.equals(normalizedQuery)) {
            return 1.0;
        }
        if (normalizedTitle.startsWith(normalizedQuery)) {
            return 0.8;
        }
        if (normalizedTitle.contains(normalizedQuery)) {
            return 0.6;
        }
        if (normalizedDescription.contains(normalizedQuery)) {
            return 0.4;
        }
        return 0;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " is required");
        }
        return value.trim();
    }
}
