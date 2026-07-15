# API Spec - Mock Test

## Base path
`/api/mock-tests`

## Authentication

- `GET /current`: authenticated frontend flow may call it, but no user-specific payload is returned.
- `POST /attempts`: requires authentication.
- `GET /attempts/{attemptId}`: requires authentication and only returns the authenticated user's attempt.

## Endpoints

### GET /api/mock-tests/current
Returns the current mock test definition without answers.

Response 200:
```json
{
  "id": "baseline-english-v1",
  "title": "Diagnostico rapido de ingles",
  "questions": [
    {
      "id": "q1",
      "question": "Qual frase esta gramaticalmente correta?",
      "options": [
        "She go to work every day.",
        "She goes to work every day.",
        "She going to work every day.",
        "She gone to work every day."
      ]
    }
  ]
}
```

### POST /api/mock-tests/attempts
Submits a full attempt, calculates score on the backend and persists the result.

Request:
```json
{
  "mockTestId": "baseline-english-v1",
  "answers": [
    {
      "questionId": "q1",
      "selectedOption": "She goes to work every day."
    }
  ]
}
```

Response 201:
```json
{
  "attemptId": "uuid",
  "mockTestId": "baseline-english-v1",
  "score": 4,
  "totalQuestions": 5,
  "recommendation": "Sua base existe, mas ainda esta instavel. Reforce revisao diaria e exercicios curtos de escrita.",
  "completedAt": "2026-07-15T12:00:00Z",
  "questions": [
    {
      "questionId": "q1",
      "selectedOption": "She goes to work every day.",
      "correctOption": "She goes to work every day.",
      "explanation": "No presente simples, he/she/it pede verbo com s.",
      "isCorrect": true
    }
  ]
}
```

Validation rules:

- all questions must be answered before submission;
- each question accepts only one selected option;
- selected option must belong to the question options;
- unknown `mockTestId` returns `404`.

### GET /api/mock-tests/attempts/{attemptId}
Returns a persisted attempt result for the authenticated user.

Response 200:
Same shape as `POST /attempts`.

## Errors

- `400 Bad Request` for incomplete or invalid submissions.
- `401 Unauthorized` for missing authentication on protected endpoints.
- `404 Not Found` for unknown mock test or attempt.
