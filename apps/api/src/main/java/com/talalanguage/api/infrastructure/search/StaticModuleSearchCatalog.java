package com.talalanguage.api.infrastructure.search;

import com.talalanguage.api.application.search.port.SearchableModuleSource;
import com.talalanguage.api.domain.search.SearchResult;
import com.talalanguage.api.domain.search.SearchResultType;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;
import org.springframework.stereotype.Component;

@Component
public class StaticModuleSearchCatalog implements SearchableModuleSource {
    private static final List<ModuleEntry> MODULES = List.of(
            new ModuleEntry("speak", "Falar", "Pratique conversacao e desenvolva fluencia.", "/speak"),
            new ModuleEntry("write", "Escrever e revisar", "Pratique escrita e receba pontos de melhoria.", "/write"),
            new ModuleEntry("review", "Revisar flashcards", "Revise palavras, frases e expressoes salvas.", "/review"),
            new ModuleEntry("community", "Comunidade", "Conheca o espaco futuro de pratica em comunidade.", "/community"),
            new ModuleEntry("progress", "Progresso", "Acompanhe sua evolucao e constancia.", "/progress"),
            new ModuleEntry("goals", "Metas", "Organize sua rotina e disponibilidade de estudo.", "/goals")
    );

    @Override
    public List<SearchResult> search(String query, int limit) {
        return MODULES.stream()
                .map(module -> SearchResult.matching(
                        SearchResultType.MODULE,
                        module.id(),
                        module.title(),
                        module.description(),
                        module.route(),
                        query
                ))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingDouble(SearchResult::score).reversed())
                .limit(limit)
                .toList();
    }

    private record ModuleEntry(String id, String title, String description, String route) {
    }
}
