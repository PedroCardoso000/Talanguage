# Layout Rules — Talanguage

## Purpose

This document defines layout rules for Talanguage screens so the existing mocked frontend can evolve into a consistent functional application.

## Current State

The frontend already has structured UI screens with mocked data. Layout work must focus on stabilization, consistency, responsiveness, and connection to real flows.

Do not rebuild layouts from scratch unless explicitly required.

## Application Layout

Authenticated screens must follow a consistent application shell:

- sidebar or main navigation;
- top/header area when needed;
- main content area;
- page header;
- content sections;
- responsive behavior.

## Page Structure

Every main page should follow this order:

1. Page header;
2. short description or next action;
3. primary action area;
4. main content cards/sections;
5. secondary information;
6. empty/error/loading states when applicable.

## Page Header

A page header should include:

- clear title;
- short description;
- optional primary action;
- optional contextual metadata.

Avoid long explanatory paragraphs.

## Navigation Rules

Main navigation should include only core product areas:

- Dashboard;
- Speaking;
- Writing Review;
- Flashcards;
- Community;
- Progress;
- Settings only when needed.

Do not add navigation items for future features before they exist.

## Dashboard Layout

The dashboard must prioritize:

- today's next action;
- daily goal status;
- progress summary;
- shortcuts to modules;
- recent activity.

Forbidden dashboard behavior:

- excessive analytics;
- too many cards;
- unrelated motivational widgets;
- complex charts in the first version.

## Module Layout

Each module page should include:

- module title;
- purpose;
- primary interaction;
- recent activity or summary;
- clear empty state.

Modules must not become independent visual products.

## Card Rules

Cards should group related information only.

A card should generally contain:

- title;
- short content;
- optional status;
- optional action.

Avoid nested cards unless necessary.

## Density Rules

Talanguage screens must be light.

Prefer:

- fewer sections;
- shorter copy;
- clear visual hierarchy;
- progressive disclosure.

Avoid:

- multiple competing primary actions;
- tables for simple data;
- overloaded dashboard cards;
- unnecessary filters.

## Responsive Rules

Layouts must work on:

- desktop;
- tablet;
- mobile.

Responsive behavior:

- sidebar may collapse;
- cards may stack;
- grids must reduce columns;
- primary action must remain visible;
- forms must remain usable.

## Loading and Empty Layouts

Every API-connected page must define:

- loading state;
- empty state;
- error state;
- retry path when applicable.

## Allowed

- preserve existing screen structure;
- simplify dense layouts;
- extract reusable layout components;
- improve responsive behavior;
- add empty/loading/error sections;
- align module pages to a shared structure.

## Forbidden

- redesign the whole shell without approval;
- add unrelated dashboard sections;
- create route-specific layouts without need;
- use tables where cards are clearer;
- create inconsistent spacing or hierarchy;
- hide primary actions below secondary information.

## Definition of Done

A layout is done when:

- it follows the shared app shell;
- it has clear hierarchy;
- it works responsively;
- it has loading/empty/error behavior when needed;
- it does not overload the user;
- it fits the existing frontend visual direction.
