# API Spec — Onboarding

## GET /api/onboarding/me

Autenticação: obrigatória.

Resposta 200 sem onboarding:
```json
{
  "completed": false,
  "onboarding": null
}
```

Resposta 200 com onboarding:
```json
{
  "completed": true,
  "onboarding": {
    "ageRange": "25_34",
    "occupation": "EMPLOYED",
    "occupationDescription": null,
    "learningMotivations": ["CAREER", "TRAVEL"],
    "primaryGoal": "Participar de reuniões em inglês.",
    "difficultySkills": ["SPEAKING", "WRITING"],
    "currentLevel": "INTERMEDIATE",
    "weeklyAvailabilityMinutes": 180,
    "completedAt": "2026-07-16T18:00:00Z"
  }
}
```

## PUT /api/onboarding/me

Autenticação: obrigatória.

Request:
```json
{
  "ageRange": "25_34",
  "occupation": "EMPLOYED",
  "occupationDescription": null,
  "learningMotivations": ["CAREER", "TRAVEL"],
  "primaryGoal": "Participar de reuniões em inglês.",
  "difficultySkills": ["SPEAKING", "WRITING"],
  "currentLevel": "INTERMEDIATE",
  "weeklyAvailabilityMinutes": 180
}
```

Resposta 200: mesmo formato de onboarding concluído do GET.

## Erros

- `400 VALIDATION_ERROR`: campo ausente, enum inválido, lista vazia/duplicada ou regra de `OTHER` violada;
- `401 UNAUTHORIZED`: autenticação ausente ou inválida.

## Auth

`GET /api/auth/me` acrescenta somente:
```json
{
  "onboardingCompleted": true
}
```

Nenhum endpoint aceita `userId` no request.
