package com.talalanguage.api.web.search.dto;

import com.talalanguage.api.domain.search.SearchResult;

public record SearchResultResponseDto(
        String type,
        String id,
        String title,
        String description,
        String route,
        double score
) {
    public static SearchResultResponseDto from(SearchResult result) {
        return new SearchResultResponseDto(
                result.type().name(),
                result.id(),
                result.title(),
                result.description(),
                result.route(),
                result.score()
        );
    }
}
