# Backend Spec — Global Search

## Use case
- `GlobalSearchUseCase` validates and normalizes input, calls selected sources, isolates only explicitly classified technical unavailability, ranks the combined results, and applies the global limit.

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
Each enabled source returns at most the API maximum of 30 candidates after applying its relevance order; the requested response limit is applied only after global scoring. Literal `%`, `_`, and `\\` are escaped before `LIKE`; other characters, including Unicode and slashes, remain literal parameters. Final order is score descending, then type, normalized title, and id for deterministic ties.

Unexpected exceptions, authentication failures, authorization failures, and programming errors propagate. Recoverable source unavailability is logged with source, correlation identifier, and exception type, without query or user data.
