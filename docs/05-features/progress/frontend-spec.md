<!--
Talanguage - Feature Specification
Contexto: o front-end ja possui UI mockada estruturada. O backend ainda nao existe.
Objetivo: orientar a transicao de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisoes fora da arquitetura documentada.
-->

# Frontend Spec - Progress

## Rota
`/progress`

## Componentes esperados
- ProgressPage;
- StreakCard;
- DailyGoalProgress;
- SkillProgressGrid;
- WeeklySummaryCard;
- LearningActivityTimeline;
- EmptyProgressState.

## Client API
`progressApiClient` com:
- `getSummary()`;
- `getActivities()`;
- `getWeeklySummary()`.

## Estados de UI
- loading;
- loaded;
- empty;
- error.

## Regras
- nao calcular streak no front-end;
- nao calcular skill progress no front-end;
- nao usar seed fixa ou estado local para cards principais de progresso;
- preservar a UI existente, trocando apenas as fontes de dados;
- estados vazios devem incentivar a primeira pratica;
- nivel estimado so pode aparecer quando existir regra real de produto e suporte no backend.
