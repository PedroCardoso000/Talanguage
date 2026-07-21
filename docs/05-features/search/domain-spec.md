# Domain Spec — Global Search

## SearchResult
Immutable search projection with:
- `type`: `SearchResultType`;
- `id`;
- `title`;
- `description`;
- `route`;
- `score`.

## SearchResultType
Declared values:
- `MODULE`;
- `FLASHCARD`;
- `SPEAKING`;
- `WRITING`;
- `COMMUNITY`.

## Rules
1. The trimmed query is required and has between 2 and 100 characters.
2. Limit defaults to 10 and must be between 1 and 30.
3. Private results always belong to the authenticated user.
4. Matching is case-insensitive.
5. Exact title match ranks before title prefix, which ranks before partial content.
6. Results never carry credentials, identity data, private answers, or persistence entities.
7. A source without trustworthy data produces no results.
