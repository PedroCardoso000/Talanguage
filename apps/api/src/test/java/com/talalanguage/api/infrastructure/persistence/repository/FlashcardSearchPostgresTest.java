package com.talalanguage.api.infrastructure.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.talalanguage.api.infrastructure.persistence.entity.FlashcardEntity;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
class FlashcardSearchPostgresTest {
    @Container
    static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.flyway.enabled", () -> "false");
    }

    @Autowired
    private FlashcardJpaRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        save("exact", "owner", "TRAVEL", "Exact");
        save("prefix", "owner", "Travel guide", "Prefix");
        save("partial", "owner", "My travel notes", "Partial");
        save("description", "owner", "Airport", "Unicode viagem travel");
        save("other-user", "other", "Travel private", "Must stay isolated");
        save("percent", "owner", "100% ready", "Literal percent");
        save("underscore", "owner", "under_score", "Literal underscore");
        save("unicode-slash", "owner", "Viagem/東京", "Unicode slash");
    }

    @Test
    void isolatesUsersMatchesCaseInsensitivelyAndOrdersCandidatesBeforeLimit() {
        List<FlashcardEntity> results = search("owner", "tRaVeL", 3);

        assertEquals(List.of("exact", "prefix", "partial"), results.stream().map(FlashcardEntity::getId).toList());
    }

    @Test
    void treatsWildcardsSlashAndUnicodeAsLiteralCharacters() {
        assertEquals(List.of("percent"), ids(search("owner", "%", 30)));
        assertEquals(List.of("underscore"), ids(search("owner", "_", 30)));
        assertEquals(List.of("unicode-slash"), ids(search("owner", "/東京", 30)));
    }

    @Test
    void appliesRequestedDatabaseLimit() {
        assertEquals(2, search("owner", "travel", 2).size());
    }

    private List<String> ids(List<FlashcardEntity> entities) {
        return entities.stream().map(FlashcardEntity::getId).toList();
    }

    private List<FlashcardEntity> search(String userId, String query, int limit) {
        String normalized = query.toLowerCase(Locale.ROOT);
        String escaped = normalized.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
        return repository.searchByUserId(
                userId,
                normalized,
                escaped + "%",
                "%" + escaped + "%",
                PageRequest.of(0, limit)
        );
    }

    private void save(String id, String userId, String front, String back) {
        repository.save(new FlashcardEntity(
                id,
                userId,
                front,
                back,
                "ENGLISH",
                "[]",
                1,
                0,
                Instant.parse("2026-07-21T12:00:00Z"),
                Instant.parse("2026-07-22T12:00:00Z")
        ));
    }
}
