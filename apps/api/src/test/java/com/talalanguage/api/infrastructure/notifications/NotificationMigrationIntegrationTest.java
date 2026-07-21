package com.talalanguage.api.infrastructure.notifications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.DriverManager;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class NotificationMigrationIntegrationTest {

    @Container
    static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16-alpine");

    @Test
    void shouldApplyAllMigrationsAndCreateNotificationDeduplicationConstraint() throws Exception {
        Flyway flyway = Flyway.configure()
                .dataSource(POSTGRES.getJdbcUrl(), POSTGRES.getUsername(), POSTGRES.getPassword())
                .load();

        flyway.migrate();

        assertEquals("10", flyway.info().current().getVersion().getVersion());
        try (var connection = DriverManager.getConnection(
                POSTGRES.getJdbcUrl(), POSTGRES.getUsername(), POSTGRES.getPassword());
             var statement = connection.prepareStatement("""
                     select indexdef
                     from pg_indexes
                     where schemaname = 'public'
                       and indexname = 'uq_notification_user_type_deduplication'
                     """)) {
            try (var result = statement.executeQuery()) {
                assertTrue(result.next());
                assertTrue(result.getString("indexdef").contains("UNIQUE"));
                assertTrue(result.getString("indexdef").contains("user_id, type, deduplication_key"));
            }
        }
    }
}
