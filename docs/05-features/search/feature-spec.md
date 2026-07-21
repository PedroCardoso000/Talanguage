# Feature Spec — Global Search

## Objective
Provide one authenticated search entry point for existing Talanguage resources without exposing private or sensitive data.

## Initial scope
- search the authenticated user's persisted flashcards;
- search public application modules from a backend static catalog;
- accept the declared `MODULE`, `FLASHCARD`, `SPEAKING`, `WRITING`, and `COMMUNITY` filters;
- omit a declared type when no trustworthy searchable source exists.

## Out of scope
- full-text search engines, embeddings, or external libraries;
- passwords, e-mail addresses, tokens, private speaking answers, and writing answers;
- fabricated results or routes;
- persisted sources that do not yet expose safe, bounded search data.

## Ready criteria
- authenticated endpoint returns ranked, bounded results;
- private results are isolated by authenticated user;
- unavailable sources do not fail the complete search;
- every returned route exists in the current application.
