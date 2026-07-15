# Domain Spec — {Feature Name}

## Purpose

Define the domain model required for this feature.

## Bounded Context

- {bounded context}

## Aggregates

### {AggregateName}

Purpose:

- {purpose}

Invariants:

- {rule}
- {rule}

## Entities

### {EntityName}

Fields:

- `id`
- `{field}`

Rules:

- {rule}

## Value Objects

### {ValueObjectName}

Fields:

- `{field}`

Validation:

- {rule}

## Domain Services

Use only when behavior does not naturally belong to an entity or aggregate.

### {DomainServiceName}

Responsibility:

- {responsibility}

## Domain Events

### {DomainEventName}

Emitted when:

- {condition}

Payload:

- `{field}`

## Business Rules

| ID | Rule |
|---|---|
| BR-001 | {rule} |

## Prohibited

- Do not model database tables as domain entities automatically.
- Do not put HTTP or UI concepts in the domain model.
- Do not create an aggregate without invariants.

## Done Criteria

This domain spec is complete when:

- aggregates are identified;
- entities are justified;
- value objects contain validation rules;
- domain events are explicit when needed;
- business rules are testable.
