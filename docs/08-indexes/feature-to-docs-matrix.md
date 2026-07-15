# Feature to Docs Matrix — Talanguage

## Purpose

Map each product feature to the exact documentation required for implementation.

This file prevents the AI from searching the whole repository and wasting tokens.

## Standard Feature Document Set

Each feature must contain:

- `feature-spec.md`
- `domain-spec.md`
- `api-spec.md`
- `backend-spec.md`
- `frontend-spec.md`
- `test-spec.md`

## Matrix

| Feature | Product Spec | Domain Spec | API Spec | Backend Spec | Frontend Spec | Test Spec | Main Skill |
|---|---|---|---|---|---|---|---|
| Auth | `docs/05-features/auth/feature-spec.md` | `docs/05-features/auth/domain-spec.md` | `docs/05-features/auth/api-spec.md` | `docs/05-features/auth/backend-spec.md` | `docs/05-features/auth/frontend-spec.md` | `docs/05-features/auth/test-spec.md` | `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md` |
| Dashboard | `docs/05-features/dashboard/feature-spec.md` | `docs/05-features/dashboard/domain-spec.md` | `docs/05-features/dashboard/api-spec.md` | `docs/05-features/dashboard/backend-spec.md` | `docs/05-features/dashboard/frontend-spec.md` | `docs/05-features/dashboard/test-spec.md` | `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md` |
| Speaking | `docs/05-features/speaking/feature-spec.md` | `docs/05-features/speaking/domain-spec.md` | `docs/05-features/speaking/api-spec.md` | `docs/05-features/speaking/backend-spec.md` | `docs/05-features/speaking/frontend-spec.md` | `docs/05-features/speaking/test-spec.md` | `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md` |
| Writing Review | `docs/05-features/writing-review/feature-spec.md` | `docs/05-features/writing-review/domain-spec.md` | `docs/05-features/writing-review/api-spec.md` | `docs/05-features/writing-review/backend-spec.md` | `docs/05-features/writing-review/frontend-spec.md` | `docs/05-features/writing-review/test-spec.md` | `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md` |
| Flashcards | `docs/05-features/flashcards/feature-spec.md` | `docs/05-features/flashcards/domain-spec.md` | `docs/05-features/flashcards/api-spec.md` | `docs/05-features/flashcards/backend-spec.md` | `docs/05-features/flashcards/frontend-spec.md` | `docs/05-features/flashcards/test-spec.md` | `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md` |
| Community | `docs/05-features/community/feature-spec.md` | `docs/05-features/community/domain-spec.md` | `docs/05-features/community/api-spec.md` | `docs/05-features/community/backend-spec.md` | `docs/05-features/community/frontend-spec.md` | `docs/05-features/community/test-spec.md` | `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md` |
| Progress | `docs/05-features/progress/feature-spec.md` | `docs/05-features/progress/domain-spec.md` | `docs/05-features/progress/api-spec.md` | `docs/05-features/progress/backend-spec.md` | `docs/05-features/progress/frontend-spec.md` | `docs/05-features/progress/test-spec.md` | `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md` |

## Feature Creation Rule

Before implementing a feature, all six feature documents must exist.

If any document is missing, the AI must create the missing documentation first using the corresponding template.

## Frontend Mock Rule

If the existing frontend has mocked data for the feature, the AI must treat the mock as UI behavior reference only.

The mock must not be treated as final backend contract.

The backend contract must be defined in `api-spec.md`.

## Done Criteria

A feature is ready for implementation when:

- all six docs exist;
- API contract is explicit;
- frontend mock dependencies are identified;
- backend responsibilities are separated from frontend behavior;
- test expectations are documented.
