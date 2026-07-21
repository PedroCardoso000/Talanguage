# Glossary — Talanguage

## Purpose

This glossary defines the shared language used across product, domain, backend, frontend, API contracts, tests, and AI execution documents.

The AI must use these terms consistently when creating code, documentation, routes, components, entities, use cases, and tests.

## Core Terms

### Talanguage

The integrated language-learning platform focused on practice, feedback, review, community, and measurable progress.

### First Version

The initial coherent version of the product. It is not the final product vision and must avoid unnecessary scope expansion.

### User

A person using Talanguage to practice and improve a target language.

### Target Language

The language the user wants to learn or practice. Initial allowed values:

- English;
- Spanish.

### Practice

Any active learning activity performed by the user, such as speaking, writing, reviewing flashcards, or completing a challenge.

### Speaking Practice

A practice flow where the user trains conversation through a simulated or assisted interaction.

### Writing Review

A practice flow where the user writes a short answer and receives feedback or correction.

### Flashcard

A review card containing a word, phrase, expression, translation, example, or usage note.

### Review

The act of revisiting previously learned words, phrases, expressions, mistakes, or feedback.

### Daily Goal

A target that encourages the user to practice consistently in a given day.

### Progress

The measurable representation of the user's learning activity, consistency, and skill development.

### Streak

A sequence of consecutive days in which the user completed at least one relevant learning activity.

### Skill

A learning dimension measured or practiced in the product. Initial skills may include:

- speaking;
- writing;
- vocabulary;
- listening, if added later;
- grammar, if added later.

### Topic

A subject used to guide speaking or writing practice. Examples:

- travel;
- work;
- introductions;
- daily routine;
- food;
- interviews.

### Level

The user's approximate proficiency level for a target language. Initial values:

- beginner;
- intermediate;
- advanced.

### Feedback

A correction, suggestion, score, or improvement note provided after a practice activity.

### Practice Content

Learner-authored text, speaking messages, system or AI replies, corrected responses and contextual feedback produced during a practice activity.

### Functional History

A private history that lets the learner revisit the actual practice content, corrections, feedback, metrics and dates needed to understand previous work and evolution.

### Private Learning Content

Practice content owned by one learner. It is private by default, must be protected by backend authorization and must not be exposed in logs or to another learner.

### Community

The area where users can discover groups, partners, or spaces for practice with other people.

### Module

A major product area, such as Dashboard, Speaking Practice, Writing Review, Flashcards, Community, or Progress.

### Feature

A specific user-facing capability inside a module.

### Use Case

An application-layer operation that executes a business action, such as starting a speaking session or submitting a writing answer.

### Entity

A domain object with identity and lifecycle.

### Value Object

A domain object defined by its value rather than identity.

### API Contract

The explicit agreement between frontend and backend defining request, response, status codes, and error format.

### Mock

A simulated data source or behavior used before real integration exists. Mocks must be isolated and easy to replace.

### AI-Assisted Behavior

A feature behavior that may be powered by AI in the future but can be simulated or stubbed in the first version.

## Naming Rules

- Use English names for code, files, classes, functions, routes, and API contracts.
- Use product-facing text according to the selected UI language strategy.
- Do not create synonyms for core domain terms unless the glossary is updated.
- Do not rename product modules without updating specs, routes, indexes, and tests.
