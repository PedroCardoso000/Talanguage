# Critical Test Notes

## Commands

- Backend: `cd apps/api && mvn test`
- Frontend lint: `npm run lint`
- Frontend build/typecheck: `npm run build`

## Current Scope

- Critical backend coverage exists for:
  - auth
  - goals
  - flashcards/review
  - writing
  - speaking
  - community
- Frontend is currently protected by lint and production build verification.

## Known Gaps

- There is no frontend test runner configured in `package.json` today.
- Because adding a new frontend test framework is outside this prompt, no UI interaction tests were added here.
- `docs/05-features/goals/test-spec.md` does not exist in the documentation tree, so goals coverage was guided by the implemented contract and current behavior.
