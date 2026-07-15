<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# API Spec — Dashboard

## Base path
`/api/dashboard`

## Endpoints

### GET /api/dashboard/summary
Retorna resumo inicial do usuário autenticado.

Response 200:
```json
{
  "userName": "Pedro",
  "currentStreakDays": 4,
  "completedActivitiesToday": 2,
  "weeklyActivityCount": 9,
  "goalCompletionPercent": 67,
  "moduleProgress": {
    "speak": 42,
    "write": 35,
    "review": 58,
    "goals": 67,
    "progress": 70,
    "mock-test": 0,
    "community": 0
  },
  "weeklyActivity": [1, 0, 2, 1, 3, 1, 1],
  "dailyGoal": {
    "target": 3,
    "completed": 1
  },
  "skillProgress": {
    "speaking": 42,
    "writing": 35,
    "vocabulary": 58,
    "consistency": 70
  },
  "recentActivities": [
    {
      "id": "uuid",
      "type": "SPEAKING",
      "title": "Travel conversation",
      "completedAt": "2026-07-07T10:00:00Z"
    }
  ],
  "nextSuggestedAction": {
    "type": "SPEAKING",
    "label": "Continue praticando fala",
    "route": "/speak"
  }
}
```

## Erros
- 401 para usuário não autenticado.

## Regras
- endpoint protegido;
- não retornar dados de outros usuários;
- manter response pequeno.
