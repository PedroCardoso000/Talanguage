# Token Economy Rules — Talanguage

## Purpose

This document defines how AI agents must reduce unnecessary token usage when working on Talanguage.

The goal is to avoid reading excessive documentation, repeating context, generating broad explanations, or implementing outside the current task.

## Central Rule

The AI must read only the documents required for the current task and must not load the entire documentation tree unless explicitly requested.

## Required Behavior

The AI must:

- use the reading order index before implementing;
- read feature-specific specs instead of global documents when possible;
- avoid repeating product context in implementation summaries;
- avoid generating long explanations when code changes are enough;
- avoid restating unchanged documentation;
- update only files affected by the task;
- prefer small, localized changes;
- reuse existing components, types, and patterns;
- reference existing contracts instead of recreating them;
- avoid large abstractions without repeated need.

## Document Reading Rules

### Small UI change

Read only:

- AI execution contract;
- design system rule directly related to the change;
- affected frontend spec;
- affected component or page.

### Backend use case change

Read only:

- AI execution contract;
- backend architecture;
- clean architecture rules;
- affected domain spec;
- affected backend spec;
- affected test spec.

### API contract change

Read only:

- AI execution contract;
- API contract rules;
- affected API spec;
- affected frontend spec;
- affected backend spec;
- affected test spec.

### Full-stack feature

Read only:

- AI execution contract;
- authorized stack;
- system overview;
- clean architecture rules;
- affected feature specs;
- full-stack feature skill;
- relevant templates.

## Output Rules

The AI must keep final summaries short and structured.

A final implementation summary should contain:

- what changed;
- where it changed;
- tests;
- assumptions;
- pending items.

The AI must not include long architecture lectures after every task.

## Prohibited Behavior

The AI must not:

- read all documentation for a simple task;
- rewrite untouched files for style only;
- duplicate specs in prompts;
- generate unused code;
- create generalized frameworks before there are repeated use cases;
- add comments that restate obvious code;
- generate large mocks when a small fixture is enough;
- create multiple alternative implementations unless explicitly requested.

## Token Budget Mindset

Every file read must have a reason.
Every generated line must serve the current task.
Every abstraction must remove actual duplication or protect an actual boundary.

## Decision Rule

If two implementation options are valid, choose the one that requires less context, fewer files, fewer dependencies, and less future explanation.
