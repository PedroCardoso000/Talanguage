package com.talalanguage.api.web.search.dto;

import com.talalanguage.api.application.search.GlobalSearchUseCase;
import java.util.List;

public record GlobalSearchResponseDto(String query, List<SearchResultResponseDto> results) {
    public static GlobalSearchResponseDto from(GlobalSearchUseCase.Result result) {
        return new GlobalSearchResponseDto(
                result.query(),
                result.results().stream().map(SearchResultResponseDto::from).toList()
        );
    }
}
