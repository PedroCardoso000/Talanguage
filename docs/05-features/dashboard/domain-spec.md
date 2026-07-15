<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Domain Spec — Dashboard

## Conceito
Dashboard não é um agregado principal. Ele é uma visão de leitura composta a partir de dados de aprendizagem.

## Read Models

### UserLearningSummary
Campos:
- userId;
- currentStreakDays;
- dailyGoalStatus;
- completedActivitiesToday;
- skillProgress;
- recentActivities;
- nextSuggestedAction.

### SkillProgressSummary
Campos:
- speaking;
- writing;
- vocabulary;
- consistency.

## Regras de domínio
1. Dashboard não deve criar regra de aprendizagem própria.
2. Deve agregar dados de outros contextos.
3. Estados vazios devem ser tratados como início de jornada, não erro.

## Eventos consumidos futuramente
- SpeakingSessionFinished;
- WritingChallengeReviewed;
- FlashcardReviewed;
- DailyGoalCompleted.
