# Frontend Current State Audit

## Scope Read

- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/01-ai-contract/token-economy-rules.md`
- `docs/02-architecture/frontend-architecture.md`
- `docs/03-design-system/ui-components.md`
- `docs/08-indexes/reading-order.md`

## 1. Existing Routes

Observed from `src/app`:

- `/`
- `/login`
- `/register`
- `/dashboard`
- `/speak`
- `/write`
- `/review`
- `/goals`
- `/progress`
- `/mock-test`

Route groups used:

- `(auth)` for public pages
- `(app)` for protected internal pages

## 2. Existing Pages

- `src/app/page.tsx`
  Redirect shell. Sends hydrated users to `/dashboard` and anonymous users to `/login`.
- `src/app/(auth)/login/page.tsx`
  Fake login form. Stores a derived user in the persisted store and redirects to `/dashboard`.
- `src/app/(auth)/register/page.tsx`
  Fake registration form. Stores user name, email and target language in the persisted store and redirects to `/dashboard`.
- `src/app/(app)/dashboard/page.tsx`
  Main overview with greeting, recommended training, module cards, weekly goal summary and progress chart.
- `src/app/(app)/speak/page.tsx`
  Simulated speaking scenario, free-text response input, generated fake feedback and local progress update.
- `src/app/(app)/write/page.tsx`
  Simulated writing challenge, textarea submission, generated fake feedback and local progress update.
- `src/app/(app)/review/page.tsx`
  Flashcard review flow with know/miss actions and local counters.
- `src/app/(app)/goals/page.tsx`
  Goal editing form persisted locally.
- `src/app/(app)/progress/page.tsx`
  Progress summary page backed by local persisted metrics.
- `src/app/(app)/mock-test/page.tsx`
  Five-question mock test with local scoring and recommendation.

## 3. Main Components

Application/layout shell:

- `src/components/app-shell.tsx`
- `src/components/sidebar.tsx`
- `src/components/topbar.tsx`
- `src/components/page-shell.tsx`
- `src/components/auth-shell.tsx`
- `src/components/auth-guard.tsx`
- `src/components/logo.tsx`

Feature-supporting components:

- `src/components/module-card.tsx`
- `src/components/stat-card.tsx`
- `src/components/progress-chart.tsx`

Local page-only helper components still embedded inside pages:

- `ScoreBox` in `src/app/(app)/speak/page.tsx`
- `Metric` in `src/app/(app)/write/page.tsx`
- `ReviewStat` in `src/app/(app)/review/page.tsx`
- `GoalInput` in `src/app/(app)/goals/page.tsx`
- `ProgressMetric` in `src/app/(app)/progress/page.tsx`

## 4. Reusable Components

Clearly reusable already:

- `AppShell`
- `AuthShell`
- `Sidebar`
- `Topbar`
- `PageShell`
- `ModuleCard`
- `StatCard`
- `ProgressChart`
- `Logo`

Reusable patterns that exist only as inline page helpers and are not yet extracted:

- score cards
- progress meters
- numeric goal fields
- feedback panels

There is no low-level UI layer yet such as `Button`, `Input`, `Textarea`, `Select`, `Card`, `Badge` or `ProgressBar`. Native elements and Tailwind classes are repeated directly in pages.

## 5. Mock Data Location

Central mock content:

- `src/data/mock-content.ts`
  Contains speaking scenarios, writing challenges, flashcards, mock test questions and weekly labels.

Persisted mocked state:

- `src/store/use-app-store.ts`
  Contains seeded `progress`, seeded `goals`, fake login/register behavior and local progress mutation rules.

Mock scoring/feedback logic:

- `src/lib/feedback.ts`
  Contains fake feedback generation and mock recommendation rules.

Type definitions:

- `src/types/index.ts`

Notable issue:

- mock data is not fully isolated; there are still page-local arrays and hardcoded strings such as dashboard module definitions and marketing cards in `AuthShell`.

## 6. Simulated Flows Already Implemented

- Auth shell redirect flow:
  `/` decides between `/login` and `/dashboard` based on persisted user state.
- Fake login flow:
  user enters email and password, store derives name from email and navigates to dashboard.
- Fake register flow:
  user enters profile data, store persists profile and navigates to dashboard.
- Protected navigation flow:
  `ProtectedGuard` blocks internal pages when no local user exists.
- Dashboard navigation flow:
  user can move from dashboard to all main modules.
- Speaking flow:
  user selects a scenario, submits text response, receives generated feedback, and increments local progress.
- Writing flow:
  user switches challenge, submits text, receives generated feedback, and increments local progress.
- Review flow:
  user cycles through flashcards, marks knew/missed, and updates local counters.
- Goals flow:
  user edits local goals and sees a saved confirmation.
- Progress flow:
  aggregated metrics render from persisted local state.
- Mock test flow:
  user answers 5 questions, gets local score, recommendation and progress update.

## 7. Gaps For Backend Integration

- No API client layer exists.
  There is no `api/`, `clients/` or `contracts/` structure.
- No typed frontend-backend contracts exist.
  Current types represent local UI state only.
- No request lifecycle states exist.
  Pages do not handle loading, backend error, retry or empty states.
- Auth is entirely local.
  No session model, token handling, refresh strategy or server validation boundary exists.
- Progress ownership is incorrect.
  Progress mutation and scoring currently live in frontend store helpers.
- Feedback ownership is incorrect.
  Speaking and writing feedback logic is generated on the client.
- Mock test evaluation ownership is incorrect.
  Answer scoring and recommendation are local.
- Search, notifications and profile controls in `Topbar` are visual only.
- There is no normalization between page view state and domain data.
- No desmocking seams were created per feature.
  Each page currently imports mocks or store actions directly instead of going through feature APIs.

## 8. Inconsistencies With Documentation

- Global state is overused.
  The architecture says to prefer local state first and use global state only when unrelated areas truly share state. Current implementation stores user, goals and all progress mutations in one global persisted store.
- Frontend owns backend/domain-like rules.
  `use-app-store.ts` and `src/lib/feedback.ts` implement scoring, progress increments, level estimation and recommendations, which the architecture explicitly says should not belong to frontend.
- No API client abstraction exists.
  The frontend architecture requires dedicated API clients per integrated feature.
- No loading/empty/error handling on integrated paths.
  The docs require those states for integrated screens; the current pages only cover idle/success mock states.
- Base UI component layer is missing.
  The design system expects reusable UI primitives, but forms and panels are mostly repeated raw markup.
- Some stable patterns remain trapped inside pages.
  The design system says extract repeated stable patterns progressively; score cards and metric panels are duplicated as inline page helpers.
- Mock data is not fully centralized.
  Mock rules are partly in `src/data/mock-content.ts`, partly in `use-app-store.ts`, partly in `src/lib/feedback.ts`, and partly inline inside page/component files.
- Page files are doing too much.
  Several pages combine view composition, flow orchestration, mock state transitions and local helper components.
- Character encoding is broken in multiple files.
  Mojibake such as `IntermediÃ¡rio`, `ApresentaÃ§Ã£o`, `OlÃ¡` appears in source reads, which is a quality issue and a documentation/UI mismatch risk.

## 9. Technical Risks

- Backend integration will be expensive if done page-by-page without extracting feature seams first.
- Business logic drift is already happening.
  Any future backend scoring or progress rules can easily conflict with current frontend heuristics.
- Persisted local state can create invalid states.
  There is no migration strategy, versioning or reset policy for `tala-language-store`.
- Hydration complexity already exists.
  `useStoreHydrated` and route guards are needed just to stabilize client persistence.
- UX states are incomplete.
  Once real APIs arrive, the current screens will need structural changes for loading, empty and error paths.
- Repeated raw form/control markup will slow down consistent evolution.
- Inline helper components encourage duplication instead of a stable component library.
- Search and topbar controls imply functionality that does not exist, which can create false expectations during demos.
- Encoding corruption can leak to production UI, tests and API payload assumptions if not corrected early.
- No tests exist around navigation, local state or page flows, so desmocking will happen without safety rails.

## 10. Suggested Desmocking Order

1. Auth
   Replace fake login/register with explicit API contracts and a real session boundary first. Everything else depends on user identity.
2. Progress read model
   Centralize dashboard and progress page data behind one typed API shape before changing scoring behavior.
3. Goals
   This is a narrow CRUD-style feature and a low-risk first integration after auth.
4. Review
   Flashcard review is simpler than speaking/writing and gives a clean pattern for read + submit + stats update.
5. Mock test
   Move question fetch and score evaluation to backend once auth and progress ingestion already exist.
6. Writing
   Replace local challenge fetch and feedback generation with backend contracts after the simpler read/submit flows are stable.
7. Speaking
   Leave this later because it currently bundles scenario selection, feedback simulation and progress mutation, and future real-time or AI-related rules will likely change more here than elsewhere.

## Summary

The current frontend is a coherent visual shell with navigable mocked flows. It is not yet structured for clean backend integration. The biggest problems are not styling; they are ownership boundaries. Progress, scoring, recommendations and fake auth all live on the client, and there is no API contract layer to replace them progressively.
