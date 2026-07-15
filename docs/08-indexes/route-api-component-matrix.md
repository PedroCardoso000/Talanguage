# Route API Component Matrix â€” Talanguage

## Purpose

Map frontend routes, backend API contracts and main UI components.

This file helps the AI connect the existing mocked frontend to future backend contracts without guessing.

## Current Context

The frontend already contains structured UI and mock data.

The backend is not yet implemented.

This matrix must be updated as APIs and components become real.

## Matrix

| Feature | Route | Main API Area | Main Page | Main Components | Notes |
|---|---|---|---|---|---|
| Auth | `/login`, `/register` | `/api/auth` | `LoginPage`, `RegisterPage` | `AuthShell`, `Input`, `Button` | Route names are preserved. Frontend auth already consumes the backend auth endpoints. |
| Dashboard | `/dashboard` | `/api/dashboard` | `DashboardPage` | `PageShell`, `ModuleCard`, `StatCard`, `ProgressChart` | Uses `dashboardApi` plus mock adapter/read model while the real backend route is pending. |
| Speaking | `/speak` | `/api/speaking` | `SpeakPage` | `PageShell`, `MetricValueCard`, `FeedbackPanel`, `ProgressBar` | Matches current route. Future backend should attach to existing flow without renaming page. |
| Writing Review | `/write` | `/api/writing` | `WritePage` | `PageShell`, `ProgressMeter`, `FeedbackPanel`, `Textarea` | Documentation uses feature name `writing-review`, but the current route remains `/write`. |
| Flashcards Review | `/review` | `/api/flashcards` | `ReviewPage` | `PageShell`, `MetricValueCard`, `FeedbackPanel`, `Button` | Current route is `/review`; API area maps to flashcards spec. |
| Goals | `/goals` | Pending spec | `GoalsPage` | `PageShell`, `NumericGoalField`, `Button` | No `api-spec.md` exists yet for this feature. Route stays as-is. |
| Progress | `/progress` | `/api/progress` | `ProgressPage` | `PageShell`, `StatCard`, `ProgressChart`, `ProgressMeter` | Uses `progressApi` plus mock adapter/read model while the real backend route is pending. |
| Mock Test | `/mock-test` | Pending spec | `MockTestPage` | `PageShell`, `Button`, `FeedbackPanel` | No `api-spec.md` exists yet for this feature. Route stays as-is. |

## API Contract Rule

The frontend must not define backend shape from mock data.

The backend shape must be defined in the feature `api-spec.md`.

## Component Rule

Existing components should be reused when they fit the documented design system.

The AI must not recreate large sections of UI only to integrate API data.

## Route Rule

The AI must not add routes unless the route is added to this matrix and the related feature spec.

Current route decisions preserved intentionally:

- `/write` remains the active route even though the feature documentation uses the name `writing-review`;
- `/review` remains the active route even though the API area is documented as `flashcards`;
- `/community` is intentionally absent from the current app and must not be added without explicit product decision.

## Desmocking Rule

When replacing mock data:

1. identify current mock source;
2. map mock fields to API response fields;
3. create or update API client;
4. update page or hook to consume API client;
5. preserve existing UI layout unless the spec requires change;
6. add loading, empty and error states;
7. update tests.

## Done Criteria

This matrix is valid when every implemented route has:

- a feature owner;
- an API area;
- a main page;
- main components;
- a documented desmocking path.
