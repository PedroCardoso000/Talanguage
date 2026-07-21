package com.talalanguage.api.application.flashcards.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.search.SearchResult;
import java.util.List;

public interface SearchableFlashcardSource {
    List<SearchResult> search(UserId userId, String query, int limit);
}
