<!--
Talanguage - Feature Specification
Contexto: o front-end ja possui UI mockada estruturada. O backend ainda nao existe.
Objetivo: orientar a transicao de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisoes fora da arquitetura documentada.
-->

# API Spec - Progress

## Base path
`/api/progress`

## Endpoints

### GET /api/progress/summary
Resumo geral.

Response 200:
```json
{
  "streakDays": 4,
  "longestStreakDays": 9,
  "lastActivityDate": "2026-07-15",
  "dailyGoal": {
    "target": 3,
    "completed": 2,
    "status": "IN_PROGRESS"
  },
  "skillProgress": {
    "speaking": 42,
    "writing": 35,
    "vocabulary": 58,
    "consistency": 70
  },
  "totalActivities": 18,
  "activityTotals": {
    "daysPracticed": 7,
    "speakingSessions": 4,
    "writingSubmissions": 3,
    "flashcardReviews": 9,
    "mockTestsCompleted": 2,
    "goalsUpdated": 0
  }
}
```

### GET /api/progress/activities
Lista historico simples.

### GET /api/progress/weekly-summary
Resumo semanal.

Response 200:
```json
{
  "weekStart": "2026-07-09",
  "weekEnd": "2026-07-15",
  "activitiesCompleted": 6,
  "streakDays": 4,
  "skillProgress": {
    "speaking": 42,
    "writing": 35,
    "vocabulary": 58,
    "consistency": 70
  },
  "dailyActivityCounts": [0, 1, 2, 0, 1, 1, 1]
}
```

## Regras
- endpoints protegidos;
- nao expor dados brutos excessivos;
- respostas devem ser pequenas e adequadas a UI;
- `summary` deve expor contadores reais para a tela de progresso sem depender de estado local;
- `weekly-summary` deve refletir apenas a janela semanal atual.
