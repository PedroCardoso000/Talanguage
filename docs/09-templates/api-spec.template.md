# API Spec — {Feature Name}

## Purpose

Define the HTTP contract between frontend and backend for this feature.

## Base Path

`/api/{feature}`

## Authentication

- Required: {yes/no}
- Roles/permissions: {roles}

## Endpoints

### {METHOD} {path}

Description:

- {description}

Request Body:

```json
{
  "field": "value"
}
```

Response {status}:

```json
{
  "field": "value"
}
```

Validation Errors:

```json
{
  "error": "VALIDATION_ERROR",
  "message": "Readable message",
  "fields": {
    "field": ["Reason"]
  }
}
```

## Error Contract

| Status | Code | Meaning |
|---|---|---|
| 400 | `VALIDATION_ERROR` | Invalid request data |
| 401 | `UNAUTHORIZED` | Authentication required |
| 403 | `FORBIDDEN` | User lacks permission |
| 404 | `NOT_FOUND` | Resource not found |
| 409 | `CONFLICT` | Business rule conflict |
| 500 | `INTERNAL_ERROR` | Unexpected error |

## Frontend Consumption Notes

- Loading state required: {yes/no}
- Empty state required: {yes/no}
- Error state required: {yes/no}

## Prohibited

- Do not expose domain entities directly.
- Do not shape backend response from frontend mock data alone.
- Do not create endpoint without use case.
- Do not return inconsistent error formats.

## Done Criteria

This API spec is complete when:

- endpoints are explicit;
- request and response examples exist;
- error behavior is documented;
- auth requirements are clear;
- frontend states are identified.
