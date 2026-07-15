# Backend Current State

## Date

2026-07-09

## Summary

`apps/api` now contains a minimal runnable Spring Boot foundation aligned with the official backend stack.

## Current Structure

- `com.talalanguage.api.TalaLanguageApiApplication`: Spring Boot entry point.
- `com.talalanguage.api.web.health.HealthController`: basic HTTP health endpoint.
- `com.talalanguage.api.application.health.GetHealthStatusUseCase`: minimal application-layer orchestration for health.
- `com.talalanguage.api.contracts.health.HealthStatusResponse`: explicit response contract.
- `com.talalanguage.api.infrastructure.config.SecurityConfig`: temporary security and CORS baseline for local development.
- `application.properties` and `application-local.properties`: base app config and local profile placeholders for PostgreSQL and frontend CORS.
- `HealthControllerTest`: API-level verification for `/api/health`.

## Decisions

- The backend foundation was repaired instead of recreated from a different stack.
- A simple health flow was implemented to prove executable Spring Boot wiring without introducing business features.
- Security is intentionally permissive for now because authentication is explicitly out of scope for this prompt.
- PostgreSQL was prepared through environment-based configuration only, without hardcoded credentials and without forcing real persistence yet.

## Commands Validated

- `mvn test`
- `mvn package`

## Validation Result

- The backend now compiles.
- The backend now packages successfully into a runnable jar.
- The health endpoint has automated coverage.

## Pending Before Auth

- Replace permissive security rules with real authentication and protected routes.
- Introduce persistence dependencies and repository implementations when the first backend feature requires them.
- Create feature-specific modules following the documented API contracts and use case boundaries.
