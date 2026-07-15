# Frontend Spec — {Feature Name}

## Purpose

Define frontend behavior for this feature.

## Current Frontend Context

The frontend may already contain a mocked UI for this feature.

Existing UI should be reused and progressively connected to backend APIs unless this spec explicitly requires layout changes.

## Route

`/{route}`

## Main Page

- `{PageName}`

## Components

- `{ComponentName}`
- `{ComponentName}`
- `{ComponentName}`

## User Flow

1. {step}
2. {step}
3. {step}

## UI States

- Idle
- Loading
- Empty
- Success
- Error

## API Client

Create or update:

- `{feature}ApiClient`

Methods:

- `{methodName}(payload)`

## State Management

Allowed state:

- local component state;
- hooks;
- query/cache library only if already allowed in stack.

## Mock Replacement Plan

1. Identify current mock source.
2. Map mock fields to API response fields.
3. Create or update API client.
4. Replace direct mock usage with API consumption.
5. Preserve layout unless change is required.
6. Add loading, empty and error states.
7. Update tests.

## Prohibited

- Do not put business rules in frontend.
- Do not create backend-shaped data inside components.
- Do not duplicate existing UI components.
- Do not redesign the page without requirement.
- Do not infer final API contract from mock data.

## Done Criteria

Frontend implementation is ready when:

- existing UI is reused where possible;
- API client is isolated;
- loading/empty/error states exist;
- mock data is removed or clearly isolated;
- page follows design system;
- tests cover core interaction.
