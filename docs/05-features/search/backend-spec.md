# Backend Spec — Global Search

## Use case
- `GlobalSearchUseCase` validates and normalizes input, calls selected sources, tolerates an unavailable source, ranks the combined results, and applies the global limit.

## Ports
- `SearchableModuleSource` for the public backend module catalog;
- `SearchableFlashcardSource` for user-owned persisted flashcards.

Future searchable modules must implement their own application port. The global use case must not depend on JPA or controllers.

## Adapters
- `StaticModuleSearchCatalog` exposes only routes that exist in the frontend;
- `JpaFlashcardSearchAdapter` executes one bounded PostgreSQL query scoped by `userId`;
- test-profile adapters remain bounded and preserve the same ownership rule.

## Current source decision
- enabled: `MODULE`, `FLASHCARD`;
- ignored: `SPEAKING` because topics are in-memory and sessions contain private responses without a resource route;
- ignored: `WRITING` because challenges are in-memory and submissions contain private answers without a resource route;
- ignored: `COMMUNITY` because groups/partners are in-memory preview data and the current UI is explicitly blocked.

## Query strategy
Candidate queries are bounded to the maximum API limit. Final order is score descending, then type, normalized title, and id for deterministic ties.
