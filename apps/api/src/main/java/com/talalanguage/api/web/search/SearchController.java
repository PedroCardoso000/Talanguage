package com.talalanguage.api.web.search;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.search.GlobalSearchUseCase;
import com.talalanguage.api.domain.search.SearchResultType;
import com.talalanguage.api.web.search.dto.GlobalSearchResponseDto;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final GlobalSearchUseCase globalSearchUseCase;

    public SearchController(GlobalSearchUseCase globalSearchUseCase) {
        this.globalSearchUseCase = globalSearchUseCase;
    }

    @GetMapping
    public GlobalSearchResponseDto search(
            Authentication authentication,
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(required = false) String types,
            @RequestParam(required = false) Integer limit
    ) {
        return GlobalSearchResponseDto.from(globalSearchUseCase.execute(new GlobalSearchUseCase.Command(
                requireUserId(authentication),
                query,
                parseTypes(types),
                limit
        )));
    }

    private Set<SearchResultType> parseTypes(String types) {
        if (types == null || types.isBlank()) {
            return EnumSet.allOf(SearchResultType.class);
        }
        try {
            EnumSet<SearchResultType> parsed = EnumSet.noneOf(SearchResultType.class);
            Arrays.stream(types.split(","))
                    .map(String::trim)
                    .filter(value -> !value.isEmpty())
                    .map(value -> value.toUpperCase(Locale.ROOT))
                    .map(SearchResultType::valueOf)
                    .forEach(parsed::add);
            if (parsed.isEmpty()) {
                throw new IllegalArgumentException("At least one search type is required.");
            }
            return parsed;
        } catch (IllegalArgumentException invalidType) {
            throw new IllegalArgumentException("Invalid search type.");
        }
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }
        return authentication.getName();
    }
}
