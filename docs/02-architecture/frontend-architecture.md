# Frontend Architecture — Talanguage

## Purpose

This document defines how the existing Talanguage frontend must be understood, preserved, evolved and integrated with the backend.

## Current State

The frontend already exists with an initial UI and mocked data.

The current frontend must be treated as an existing product shell, not as disposable code.

The goal is not to recreate the frontend from scratch. The goal is to stabilize its structure, remove ambiguity, connect it to backend contracts and progressively replace mocks with real API calls.

## Frontend Responsibilities

The frontend is responsible for:

- page rendering;
- navigation;
- layout composition;
- user interaction;
- form handling;
- API calls;
- client-side loading and error states;
- display of progress, routines and practice flows;
- visual consistency.

The frontend is not responsible for:

- domain business rules;
- authorization decisions beyond UI visibility;
- persistence rules;
- scoring rules that belong to backend/domain;
- AI provider orchestration;
- data ownership.

## Recommended Structure

If the current frontend already has a structure, preserve it when it is coherent.

When adjustments are needed, move toward this structure:

```txt
apps/web/src/
├── app/
│   ├── routes/
│   └── providers/
├── pages/
│   ├── auth/
│   ├── dashboard/
│   ├── speaking/
│   ├── writing-review/
│   ├── flashcards/
│   ├── community/
│   └── progress/
├── features/
│   ├── auth/
│   ├── dashboard/
│   ├── speaking/
│   ├── writing-review/
│   ├── flashcards/
│   ├── community/
│   └── progress/
├── components/
│   ├── ui/
│   ├── layout/
│   └── shared/
├── api/
│   ├── clients/
│   └── contracts/
├── data/
│   └── mocks/
├── hooks/
├── types/
├── utils/
└── styles/
```

## Page Rules

Pages must:

- compose feature components;
- handle page-level loading and error states;
- not contain raw mock data;
- not contain backend business rules;
- not contain large reusable UI logic;
- remain readable and small.

## Component Rules

Components must be separated by responsibility.

### UI Components

Generic visual components such as:

- Button;
- Card;
- Input;
- Textarea;
- Badge;
- Modal;
- ProgressBar;
- Select;
- Tabs.

UI components must not know product-specific business rules.

### Layout Components

Layout components include:

- AppShell;
- Sidebar;
- Header;
- NavigationMenu;
- PageContainer.

### Feature Components

Feature components belong to a specific product module.

Examples:

- SpeakingSetupCard;
- ConversationPanel;
- WritingPromptCard;
- FlashcardReviewPanel;
- CommunityGroupCard;
- ProgressSummaryCard.

## API Client Rules

Each integrated feature must have a dedicated API client or service file.

Example:

```txt
apps/web/src/features/speaking/api/speaking-api.ts
```

The API client must:

- call documented endpoints;
- use typed request/response contracts;
- isolate HTTP details from UI components;
- expose simple methods for feature usage.

UI components must not call `fetch` or HTTP clients directly.

## Mock Data Rules

Mock data is allowed only while a feature is not connected to the backend.

Mock data must be stored outside UI components.

Recommended location:

```txt
apps/web/src/data/mocks/
```

or inside a feature-specific mock folder:

```txt
apps/web/src/features/{feature}/mocks/
```

Mock data must be removed or clearly marked once the feature is integrated.

## State Management Rules

Use the simplest state model possible.

Allowed order of preference:

1. local component state;
2. feature-level hooks;
3. URL state when navigation/search depends on it;
4. server-state library only when justified;
5. global state only when multiple unrelated areas need the same state.

Do not add global state management for isolated page flows.

## Forms

Forms must:

- keep validation explicit;
- avoid hidden side effects;
- call feature-level functions or API clients;
- show loading, success and error feedback;
- not embed backend rules directly.

## Routing Rules

Routes must follow the documented route matrix.

Do not create new routes without updating:

- feature spec;
- route/API/component matrix;
- navigation rules.

## Error Handling

Each integrated screen must handle:

- loading state;
- empty state;
- validation error;
- backend error;
- unexpected error.

Error messages must be human-readable and aligned with product tone.

## Progressive Desmocking

When converting a mocked screen into a functional screen:

1. identify mock source;
2. create or validate API contract;
3. create frontend API client;
4. replace mock usage with API call;
5. keep fallback mock only when explicitly allowed;
6. update tests;
7. remove dead mock data.

## Forbidden

The frontend must not:

- own backend/domain rules;
- duplicate scoring algorithms from backend;
- hide mock data inside components;
- create endpoints implicitly;
- depend on database models;
- introduce new libraries without authorization;
- create visual patterns outside the design system;
- rewrite existing UI without reason.

## Definition of Done

A frontend change is done when:

- it respects the existing UI direction;
- it uses documented components or creates justified ones;
- it does not hide mock data;
- it uses API clients for backend communication;
- it handles loading, empty and error states when applicable;
- it follows the feature frontend spec;
- it does not introduce unauthorized dependencies.
