# Out of Scope — Talanguage First Version

## Purpose

This document defines what must not be implemented in the first version of Talanguage unless explicitly approved through a documented architectural or product decision.

The goal is to protect the product from scope creep, unnecessary cost, and AI-generated features outside the intended direction.

## Product Out of Scope

The first version must not include:

- a teacher marketplace;
- live paid classes;
- full social network behavior;
- public user feeds;
- ranking systems focused on vanity metrics;
- complex gamification;
- enterprise classroom management;
- school administration features;
- certification flows;
- marketplace payments;
- creator tools for teachers;
- large course catalog;
- video lesson library;
- clone behavior from Duolingo, Cambly, ChatGPT, Discord, or other tools.

## Speaking Practice Out of Scope

The first version must not include by default:

- real-time audio calls;
- WebRTC;
- speech-to-text transcription;
- pronunciation scoring;
- phonetic correction;
- voice cloning;
- real human matching for live calls;
- unrestricted AI conversation without cost control.

## Writing Review Out of Scope

The first version must not include by default:

- advanced essay grading;
- academic writing certification;
- plagiarism detection;
- complex grammar taxonomy;
- unlimited AI feedback generation;
- long-form writing workflows.

## Community Out of Scope

The first version must not include by default:

- real-time chat;
- audio rooms;
- video rooms;
- moderation tooling;
- public profiles with social feeds;
- follow/follower mechanics;
- direct messaging;
- complex notifications.

## Flashcards Out of Scope

The first version must not include by default:

- advanced spaced repetition algorithms unless explicitly defined;
- imported decks from external platforms;
- public deck marketplace;
- collaborative deck editing.

## Progress Out of Scope

The first version must not include by default:

- advanced analytics dashboards;
- predictive learning models;
- skill certification;
- complex cohort analytics;
- administrative reporting.

## Technical Out of Scope

The first version must not include:

- microservices;
- distributed messaging;
- event streaming;
- WebRTC infrastructure;
- complex background jobs;
- unnecessary CQRS complexity;
- GraphQL unless explicitly approved;
- multi-tenant enterprise architecture;
- mobile native apps;
- desktop apps;
- offline-first architecture;
- premature scaling architecture.

## AI Out of Scope

Real AI integration is out of scope until the following are documented:

- provider selection;
- prompt contracts;
- usage limits;
- cost model;
- data privacy rules;
- abuse prevention;
- observability strategy;
- fallback behavior.

## Payment Out of Scope

The first version must not implement:

- subscription billing;
- coupons;
- invoices;
- payment gateway integration;
- plan enforcement;
- revenue analytics.

These may be planned later, but they are not part of the initial product foundation unless explicitly moved into scope.

## Rule

If a requested implementation touches any out-of-scope item, the AI must stop and request explicit product or architectural approval before proceeding.
