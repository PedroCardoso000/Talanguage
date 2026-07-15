# Prompt Feature Template — Talanguage

Use this prompt to ask an AI agent to implement a feature with controlled context.

---

Implement the feature `{Feature Name}` for Talanguage.

## Current Context

The frontend already has structured UI and may contain mocked data.

Do not recreate the frontend from scratch.

The backend must follow the documented architecture and API contract.

## Read Only These Documents

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/01-ai-contract/token-economy-rules.md`
3. `docs/01-ai-contract/allowed-stack.md`
4. `docs/02-architecture/system-overview.md`
5. `docs/02-architecture/backend-architecture.md`
6. `docs/02-architecture/frontend-architecture.md`
7. `docs/02-architecture/clean-architecture-rules.md`
8. `docs/02-architecture/api-contract-rules.md`
9. `docs/05-features/{feature}/feature-spec.md`
10. `docs/05-features/{feature}/domain-spec.md`
11. `docs/05-features/{feature}/backend-spec.md`
12. `docs/05-features/{feature}/api-spec.md`
13. `docs/05-features/{feature}/frontend-spec.md`
14. `docs/05-features/{feature}/test-spec.md`
15. `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## Execute in This Order

1. Validate first-version scope.
2. Identify existing frontend mocks and components.
3. Implement or update domain model.
4. Implement application use cases.
5. Implement ports/interfaces.
6. Implement infrastructure only where needed.
7. Implement DTOs and API endpoints.
8. Implement backend tests.
9. Create or update frontend API client.
10. Replace mock usage progressively.
11. Preserve existing UI layout unless spec requires change.
12. Add loading, empty and error states.
13. Implement frontend tests where applicable.
14. Validate API contract.
15. Validate architecture boundaries.
16. Return final summary.

## Do Not

- Do not add dependencies.
- Do not create new routes unless documented.
- Do not redesign existing UI without requirement.
- Do not implement AI provider integration unless approved.
- Do not implement WebRTC, real-time chat or microservices.
- Do not put business rules in frontend.
- Do not put business rules in controllers.
- Do not infer backend contract from mock data.

## Final Response Format

Return:

- files created;
- files modified;
- tests created or updated;
- mocks removed or preserved;
- assumptions made;
- pending decisions;
- validation checklist.
