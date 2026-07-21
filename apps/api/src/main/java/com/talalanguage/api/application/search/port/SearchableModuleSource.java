package com.talalanguage.api.application.search.port;

import com.talalanguage.api.domain.search.SearchResult;
import java.util.List;

public interface SearchableModuleSource {
    List<SearchResult> search(String query, int limit);
}
