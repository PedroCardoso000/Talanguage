package com.talalanguage.api.infrastructure.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.talalanguage.api.infrastructure.persistence.entity.FlashcardEntity;
import jakarta.persistence.EntityManagerFactory;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FlashcardSearchPostgresTest {
    @Container
    static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
        registry.add("spring.jpa.properties.hibernate.generate_statistics", () -> "true");
        registry.add("spring.jpa.properties.hibernate.session.events.log", () -> "false");
    }

    @Autowired
    private FlashcardJpaRepository repository;

    @Autowired
    private Flyway flyway;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        saveUser("owner", "owner@example.com");
        saveUser("other", "other@example.com");
        save("exact", "owner", "TRAVEL", "Exact");
        save("prefix", "owner", "Travel guide", "Prefix");
        save("partial", "owner", "My travel notes", "Partial");
        save("description", "owner", "Airport", "Unicode viagem travel");
        save("other-user", "other", "Travel private", "Must stay isolated");
        save("percent", "owner", "100% ready", "Literal percent");
        save("underscore", "owner", "under_score", "Literal underscore");
        save("unicode-slash", "owner", "Viagem/東京", "Unicode slash");
        save("backslash", "owner", "folder\\name", "Literal backslash");
    }

    @Test
    void appliesAllFlywayMigrationsToPostgres16() {
        assertEquals("10", flyway.info().current().getVersion().getVersion());
        assertTrue(POSTGRES.getDockerImageName().startsWith("postgres:16"));
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
        assertEquals(List.of("backslash"), ids(search("owner", "\\", 30)));
    }

    @Test
    void appliesRequestedDatabaseLimit() {
        assertEquals(2, search("owner", "travel", 2).size());
    }

    @Test
    void executesSearchWithOneDatabaseQuery() {
        repository.flush();
        var statistics = entityManagerFactory.unwrap(SessionFactory.class).getStatistics();
        statistics.clear();

        search("owner", "travel", 30);

        assertEquals(1, statistics.getPrepareStatementCount());
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

    private void saveUser(String id, String email) {
        jdbcTemplate.update("""
                insert into app_user (id, name, email, password_hash, target_language, created_at, updated_at)
                values (?, ?, ?, ?, ?, ?, ?)
                on conflict (id) do nothing
                """,
                id,
                "Search Owner",
                email,
                "hashed-password",
                "ENGLISH",
                Timestamp.from(Instant.parse("2026-07-21T12:00:00Z")),
                Timestamp.from(Instant.parse("2026-07-21T12:00:00Z"))
        );
    }
}
