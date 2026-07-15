# Visual Identity — Talanguage

## Purpose

This document defines the visual direction for Talanguage so future AI-generated UI changes remain consistent with the existing mocked frontend instead of producing disconnected screens.

The current frontend already has structured screens and mocked data. Future work must preserve the established visual base, refine it where necessary, and connect it to real flows progressively.

## Product Personality

Talanguage must feel:

- practical;
- modern;
- focused;
- calm;
- trustworthy;
- adult;
- progress-oriented;
- lightweight.

The product must not feel childish, noisy, overly gamified, or like a generic course platform.

## Visual Principles

### 1. Practice First

The interface must guide the user toward action: speak, write, review, practice, and track progress.

Screens must not become static content pages.

### 2. Low Cognitive Load

Each screen must present a small number of clear decisions. The user should quickly understand what to do next.

### 3. Clear Progress

Progress indicators must communicate movement, not vanity. Use progress bars, streaks, skill summaries, and completion states only when they help the user understand learning evolution.

### 4. Integrated Ecosystem

Modules must look like parts of one platform, not separate apps. Speaking, writing, flashcards, community, and progress must share the same visual language.

### 5. Adult Motivation

The product can be encouraging, but not childish. Avoid excessive badges, cartoon-like gamification, and playful overload.

## Existing Frontend Rule

Because the frontend UI already exists in mocked form:

- do not redesign screens from scratch unless explicitly requested;
- preserve the established layout direction;
- reuse current visual patterns where they are coherent;
- improve only what harms clarity, accessibility, responsiveness, or integration;
- remove excessive information instead of adding more elements;
- prefer refinement over reinvention.

## Color Usage

The color system must support:

- primary actions;
- secondary actions;
- neutral surfaces;
- subtle dividers;
- success states;
- warning states;
- error states;
- progress indicators.

Colors must be used consistently through tokens or shared classes/components.

## Typography

Typography must prioritize readability.

Rules:

- headings must define hierarchy clearly;
- body text must be short and scannable;
- labels must be direct;
- helper text must reduce ambiguity;
- avoid decorative typography.

## Iconography

Icons may be used to improve scanning, but they must not replace labels in important actions.

Allowed uses:

- module navigation;
- cards;
- status indicators;
- empty states;
- lightweight visual support.

Forbidden uses:

- icon-only primary actions without label;
- inconsistent icon families;
- decorative icon overload.

## Motion

Motion must be minimal and functional.

Allowed:

- simple hover states;
- focus transitions;
- loading states;
- small progress transitions.

Forbidden unless explicitly approved:

- complex animations;
- gamified animation sequences;
- animated mascots;
- distracting transitions between core screens.

## Accessibility Direction

The UI must support:

- visible focus states;
- sufficient contrast;
- keyboard navigation for forms and actions;
- clear labels;
- predictable navigation;
- responsive layouts.

## Allowed

- refine existing mocked screens;
- standardize visual patterns;
- extract reusable UI components;
- simplify dense screens;
- improve hierarchy and readability;
- connect UI states to real backend flows.

## Forbidden

- redesign the entire product without approval;
- add childish gamification;
- create dense dashboards with too many metrics;
- create visual patterns not aligned with existing UI;
- add new UI libraries without approval;
- create inconsistent button/card/input styles.

## Definition of Ready for UI Changes

Before changing a screen, the AI must know:

- which module is being changed;
- whether the change is visual, functional, or integration-related;
- which current mocked state exists;
- which real API state will replace the mock;
- which reusable components already exist.

## Definition of Done

A visual change is done when:

- it preserves the Talanguage identity;
- it does not conflict with existing screens;
- it improves clarity or functionality;
- it uses existing components or justified new components;
- it remains responsive;
- it does not introduce visual noise.
