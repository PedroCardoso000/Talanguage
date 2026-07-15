# UI Components — Talanguage

## Purpose

This document defines the reusable UI component direction for Talanguage.

The current frontend already contains mocked screens. Future AI work must identify reusable patterns, avoid duplication, and progressively extract stable components without breaking existing UI.

## Component Strategy

Components must be simple, composable, typed, and aligned with the existing frontend design.

The AI must not create a large component system before there is repeated need.

## Component Categories

### Base UI Components

Reusable low-level components:

- Button;
- Input;
- Textarea;
- Select;
- Badge;
- Card;
- Modal;
- ProgressBar;
- Tabs;
- Avatar;
- EmptyState;
- LoadingState;
- ErrorState.

### Layout Components

Application structure components:

- AppShell;
- Sidebar;
- Header;
- PageContainer;
- PageHeader;
- Section;
- Grid;
- ModuleLayout.

### Product Components

Reusable product-specific components:

- ModuleCard;
- DailyGoalCard;
- PracticeSummaryCard;
- SkillProgressCard;
- LanguageSelector;
- LevelSelector;
- TopicSelector;
- ActivityList;
- FeedbackPanel;
- ReviewCard.

### Feature Components

Components specific to one module:

- SpeakingSetupCard;
- ConversationPanel;
- WritingChallengeCard;
- WritingFeedbackCard;
- FlashcardReviewPanel;
- CommunityGroupCard;
- ProgressSkillChart.

Feature components may be promoted to product components only when reused by more than one module.

## Component Rules

### Naming

Use clear names based on function, not visual appearance.

Good:

- `PracticeSummaryCard`
- `LanguageSelector`
- `WritingFeedbackCard`

Bad:

- `BlueCard`
- `NiceBox`
- `BigPanel`

### Props

Props must be explicit and typed.

Avoid generic prop bags such as:

- `data: any`;
- `config: object`;
- `options: any[]` without type.

### Responsibility

A component must have one primary responsibility.

Do not mix:

- API calls;
- business rules;
- complex state orchestration;
- visual rendering;
- domain decisions.

### Data

Mocked data must not live inside reusable components.

Allowed locations:

- feature mock files;
- API client mocks;
- test fixtures;
- development-only adapters.

Forbidden:

- hardcoded mock arrays inside generic UI components;
- backend-like rules inside frontend components;
- hidden fake responses inside visual components.

## Existing Frontend Rule

When a mocked screen already exists:

1. identify repeated visual patterns;
2. extract only stable patterns;
3. avoid changing the layout unnecessarily;
4. preserve current UX unless a documented spec requires change;
5. replace mock wiring progressively with API clients.

## State Components

Every async or API-connected screen should have clear UI states:

- idle;
- loading;
- success;
- empty;
- error;
- disabled when needed.

Do not leave screens that silently fail.

## Form Components

Forms must support:

- label;
- input;
- helper text;
- error message;
- disabled state;
- loading submit state.

Form validation rules must come from feature specs or shared validation contracts.

## Allowed

- create small reusable components;
- extract duplication from mocked screens;
- create feature-level components;
- add typed props;
- add clear loading/empty/error states;
- align components with API contracts.

## Forbidden

- introduce a new UI library without approval;
- create huge generic components;
- create components that know backend implementation details;
- duplicate existing components;
- create mock data inside UI components;
- create visual variants that are not used.

## Definition of Done

A UI component is ready when:

- it has a clear name;
- it has typed props;
- it has one responsibility;
- it is reusable or clearly feature-scoped;
- it handles relevant states;
- it does not contain hidden mock data;
- it follows the visual identity.
