# Current Functional Gap Audit

## Scope Read

- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/01-ai-contract/token-economy-rules.md`
- `docs/02-architecture/system-overview.md`
- `docs/02-architecture/backend-architecture.md`
- `docs/02-architecture/frontend-architecture.md`
- `docs/07-plans/010-frontend-alignment.md`
- `docs/12-prompts-production-springboot-fix/README.md`

## Executive Summary

The project is no longer just a mocked shell. It already has a real Spring Boot API, authenticated flows, frontend API clients and working end-to-end interactions for auth, goals, review, writing, speaking, dashboard, progress and community.

The hard truth is this: it is still not a genuinely persistent product.

The backend currently stores runtime data in memory, not in PostgreSQL. Some frontend modules still depend on local Zustand data to compose final UI metrics. The mock test is still fully local/mock. Profile and notifications do not exist as features yet. Community is still a live module even though the new stabilization track says it should move to a blurred "next version" state.

So the current state is:

- demoable;
- partially functional;
- architecturally improved;
- not durably persistent;
- not aligned with the next stabilization expectations yet.

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
- `/community`

Route groups:

- `(auth)` for public pages
- `(app)` for authenticated pages

Not present:

- `/profile`
- `/notifications`

## 2. Functionalities That Are Really Functional

### Frontend + backend working end-to-end

- Auth
  - register
  - login
  - current session lookup
  - logout
- Dashboard
  - page loads through backend summary endpoint
  - loading and error states exist
- Goals
  - read current goals from backend
  - update goals through backend
- Review / flashcards
  - list cards from backend
  - submit review result to backend
  - read review stats from backend
- Writing
  - fetch challenges
  - submit writing answer
  - fetch recent submissions
- Speaking
  - fetch topics
  - start session
  - submit text response
  - finish session
  - fetch recent sessions
- Progress
  - fetch summary
  - fetch activities
  - fetch weekly summary
- Community
  - fetch groups
  - fetch partners
  - register interest

### Frontend runtime behavior that works

- route guards for authenticated and public pages
- loading and error branches for the integrated screens
- shell navigation between internal modules
- local persisted session token handling

## 3. Functionalities That Are Only Visual or Still Mocked

### Fully mocked or locally simulated

- Mock test
  - `src/features/mock-test/api/mock-test-api.ts` uses `mockTestApiAdapter`
  - no backend endpoint exists for questions or scoring
  - score and recommendation are computed locally
  - persisted effect is only local Zustand progress mutation

### Partially functional but still mixed with local fake state

- Dashboard
  - fetches backend summary
  - still injects local Zustand values such as `weeklyActivity` and target language into the final read model
- Progress
  - fetches backend summary, activities and weekly summary
  - still renders `localStats` from frontend Zustand, not from server persistence
- Training streak
  - still driven by frontend local progress state, not durable backend persistence
- Overall progress percentages and some read-model composition
  - still depend on local store values and seeded mock defaults

### Visual only or not implemented as real feature

- Notifications
  - bell exists in topbar
  - no route, no backend, no behavior
- Profile management
  - no page, no edit flow, no avatar/photo upload flow
- Search in topbar
  - input exists visually
  - no search feature behind it

### Functionally real but still heuristic/unrealistic

- Writing feedback
  - backend-generated, but still heuristic/simple, not credible "real correction"
- Speaking feedback
  - backend-generated, but still heuristic/simple, not credible "real evaluation"

## 4. Existing Spring Boot Backend And Current Endpoints

### Auth

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/auth/me`
- `POST /api/auth/logout`

### Health

- `GET /api/health`

### Dashboard

- `GET /api/dashboard/summary`

### Goals

- `GET /api/goals`
- `PUT /api/goals`

### Flashcards / review

- `GET /api/flashcards`
- `POST /api/flashcards`
- `POST /api/flashcards/{id}/review`
- `GET /api/flashcards/stats`

### Writing

- `GET /api/writing/challenges`
- `POST /api/writing/submissions`
- `GET /api/writing/submissions/recent`

### Speaking

- `GET /api/speaking/topics`
- `POST /api/speaking/sessions`
- `POST /api/speaking/sessions/{sessionId}/responses`
- `POST /api/speaking/sessions/{sessionId}/finish`
- `GET /api/speaking/sessions/recent`

### Progress

- `GET /api/progress/summary`
- `GET /api/progress/activities`
- `GET /api/progress/weekly-summary`

### Community

- `GET /api/community/groups`
- `GET /api/community/partners`
- `POST /api/community/interests`

## 5. Existing Entities / Tables

### Domain entities and value objects exist in code

Observed under `apps/api/src/main/java/com/talalanguage/api/domain`:

- Auth
  - `User`
  - `UserId`
  - `Email`
  - `AuthenticatedSession`
- Goals
  - `GoalSettings`
- Progress
  - `LearningActivity`
  - `DailyGoal`
  - `ActivityType`
  - `SkillType`
- Flashcards
  - `Flashcard`
  - `FlashcardLanguage`
  - `ReviewRating`
- Writing
  - `WritingChallenge`
  - `WritingSubmission`
  - `WritingFeedback`
  - `WritingLanguage`
  - `WritingLevel`
  - `WritingSubmissionStatus`
- Speaking
  - `SpeakingTopic`
  - `SpeakingSession`
  - `SpeakingFeedback`
  - `SpeakingLanguage`
  - `SpeakingLevel`
  - `SpeakingSessionStatus`
- Community
  - `PracticeGroup`
  - `PracticePartnerProfile`
  - `CommunityInterest`
  - `CommunityLanguage`
  - `CommunityLevel`
  - `CommunityInterestTargetType`

### Real database tables

None confirmed.

Reasons:

- no JPA dependency in `apps/api/pom.xml`
- no `@Entity` classes found
- no `JpaRepository` or `CrudRepository` usage found
- no Flyway dependency or migration files found

So the backend has domain modeling, but not relational persistence yet.

## 6. Points Without Real Persistence

### Backend runtime persistence is in memory

Observed through current infrastructure classes:

- `InMemoryUserRepository`
- `InMemorySessionService`
- `InMemoryGoalSettingsRepository`
- `InMemoryFlashcardRepository`
- `InMemoryFlashcardStatsRepository`
- `InMemoryLearningActivityRepository`
- `InMemoryWritingChallengeRepository`
- `InMemoryWritingSubmissionRepository`
- `InMemorySpeakingTopicRepository`
- `InMemorySpeakingSessionRepository`
- `InMemoryPracticeGroupRepository`
- `InMemoryPracticePartnerQueryService`
- `InMemoryCommunityInterestRepository`

Impact:

- users disappear when API restarts
- sessions disappear when API restarts
- goals disappear when API restarts
- writing submissions disappear when API restarts
- speaking sessions disappear when API restarts
- review stats disappear when API restarts
- community interests disappear when API restarts
- progress history disappears when API restarts

### Frontend local persistence still acts like source of truth in places

- `src/store/use-app-store.ts`
  - still mutates progress, streak, scores, level and mock-test effects locally
- `src/features/progress/lib/read-model.ts`
  - still mixes backend responses with local Zustand state for dashboard/progress read models
- `src/features/mock-test/api/mock-test-api.ts`
  - still uses mock adapter instead of backend

### Database configuration exists but is not active in real persistence

`application-local.properties` contains:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

But these values do not back actual persistence yet because the API still uses in-memory implementations and has no JPA/Flyway stack enabled.

## 7. Recommended Order Of Correction

### 1. Establish real persistence baseline

Do this first. Anything else on top of ephemeral memory is fake progress.

Priority:

- add real PostgreSQL persistence path
- introduce actual schema strategy
- replace in-memory auth, goals and progress repositories first

### 2. Replace dashboard and progress fake/local composition

These screens currently look more real than they are. Stop mixing backend summary with local Zustand metrics and move streak/progress ownership to the backend.

### 3. Fix mock test properly

It still has no backend. Move questions, submission and scoring to Spring Boot so the module stops being a front-end toy.

### 4. Add profile management

This is a missing product surface, not just polish. User identity currently ends at login/register with no profile route or editable attributes.

### 5. Add notifications only after persistence exists

Right now the bell is decorative. Do not build notifications on top of ephemeral stores.

### 6. Make writing and speaking feedback more credible

They are functional but still simplistic. Improve contracts and backend evaluation behavior only after persistence and read-model ownership are fixed.

### 7. Decide community strategy

The stabilization prompt says community should move to a blurred "next version" state. The current product still exposes it as active. Resolve that conflict explicitly instead of drifting.

### 8. Clean system copy and UX language

There are still messages without proper polish and some mixed-language fallback errors. Clean this after functional ownership is fixed, not before.

## 8. Risks Of Changing Everything At Once

- You will break the only thing the project currently has: a demoable integrated flow.
- You will mix persistence migration, API evolution, UI changes and copy rewrites into one noisy batch with no clear blame when something fails.
- You will duplicate rules again if backend persistence and frontend local state are changed in parallel without a single source of truth decision.
- You will hide real regressions because everything will appear "changed" at the same time.
- You will waste time polishing community, profile or notifications before the persistence layer is trustworthy.
- You will make auth and progress bugs harder to isolate if mock-test, writing, speaking and dashboard are all rewritten together.

## Recommended Stabilization Strategy

Treat the next phase as a sequence, not a rewrite:

1. persistence baseline
2. auth/goals/progress ownership cleanup
3. mock-test backendization
4. profile
5. notifications
6. writing/speaking credibility improvements
7. community product-direction adjustment

## Final Assessment

The current system is stronger than a mock MVP and weaker than a real persistent SaaS.

What works:

- route shell
- auth flow
- integrated API clients
- real Spring Boot endpoints
- end-to-end demo flows

What is still fake or unstable:

- durable persistence
- streak and progress truth source
- mock test
- profile
- notifications
- community direction

If you pretend this is already "fully functional", you will build the next layer on a lie. The correct move is not to rewrite everything. The correct move is to remove the remaining fake foundations in the right order.
