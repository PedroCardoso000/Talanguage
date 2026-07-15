# Responsive Rules — Talanguage

## Purpose

This document defines responsive behavior for Talanguage so existing mocked screens can become usable across devices without visual fragmentation.

## Target Devices

The first version must support:

- desktop;
- laptop;
- tablet;
- mobile.

Desktop can remain the primary design target, but mobile must not be broken.

## General Rules

### Desktop

Desktop layouts may use:

- sidebar navigation;
- two-column sections;
- dashboard grids;
- wider cards;
- persistent context panels.

### Tablet

Tablet layouts should:

- reduce grid columns;
- preserve readable spacing;
- avoid horizontal overflow;
- keep primary actions visible.

### Mobile

Mobile layouts should:

- stack cards vertically;
- collapse navigation;
- keep forms single-column;
- keep action buttons full-width when appropriate;
- avoid dense data sections.

## Navigation Behavior

Responsive navigation may use:

- collapsed sidebar;
- top navigation;
- drawer navigation;
- bottom navigation only if explicitly approved.

Do not create multiple competing navigation systems.

## Grid Rules

Grids must adapt:

- 3 columns on wide desktop only when content supports it;
- 2 columns for medium screens;
- 1 column for mobile.

Do not force fixed-width card grids.

## Form Rules

Forms must remain usable on mobile:

- labels visible;
- inputs full-width;
- error messages close to fields;
- submit action visible;
- no horizontal scrolling.

## Practice Flow Rules

Practice screens must preserve the core action on small screens.

For speaking, writing, flashcards, and review flows:

- primary practice area must appear before secondary history;
- feedback must be readable;
- actions must be reachable without excessive scrolling.

## Dashboard Rules

On mobile:

- daily next action comes first;
- goal status comes second;
- module shortcuts come after;
- detailed progress comes later.

Do not show complex multi-chart dashboards on mobile.

## Typography Rules

Text must remain readable:

- avoid tiny labels;
- avoid long line lengths;
- avoid cramped card text;
- keep headings proportional.

## Overflow Rules

Forbidden:

- horizontal scrolling on main pages;
- clipped buttons;
- hidden form labels;
- fixed-width tables;
- content that depends on hover only.

## Allowed

- stack content vertically;
- collapse secondary panels;
- simplify dashboard sections;
- use responsive utility classes;
- create mobile-specific layout adjustments when necessary.

## Forbidden

- create separate mobile-only screens;
- duplicate business logic for responsiveness;
- hide core functionality on mobile;
- create inconsistent mobile navigation;
- rely on hover interactions.

## Definition of Done

A responsive screen is done when:

- it works without horizontal scroll;
- primary actions remain visible and usable;
- forms are readable;
- cards stack cleanly;
- navigation is accessible;
- content priority matches the learning flow.
