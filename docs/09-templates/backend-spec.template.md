# Backend Spec — {Feature Name}

## Purpose

Define backend responsibilities for this feature.

## Related Docs

- `feature-spec.md`
- `domain-spec.md`
- `api-spec.md`
- `test-spec.md`

## Application Use Cases

### {UseCaseName}

Responsibility:

- {responsibility}

Input:

- `{field}`

Output:

- `{field}`

Rules:

- {rule}

Dependencies:

- {repository/interface/service}

## Repositories / Ports

### {RepositoryName}

Methods:

- `{methodName}({params})`

## Infrastructure

Persistence:

- {database/in-memory/none}

External services:

- {service/none}

## DTOs

Input DTOs:

- `{DtoName}`

Output DTOs:

- `{DtoName}`

## Validation

- {validation rule}

## Prohibited

- Do not put business rules in controllers.
- Do not expose domain entities as API responses.
- Do not create infrastructure before the interface/port exists.
- Do not persist data that is not required by the feature spec.
- Do not implement AI provider integration unless explicitly approved.

## Done Criteria

Backend implementation is ready when:

- use cases exist;
- domain rules are respected;
- ports/interfaces are explicit;
- infrastructure implements ports;
- DTOs are separated from domain;
- tests cover relevant use cases.
