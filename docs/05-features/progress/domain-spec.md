<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Domain Spec — Progress

## Agregados/Entidades

### LearningActivity
Representa uma atividade concluída.

Campos:
- id;
- userId;
- type;
- skill;
- score;
- completedAt;
- sourceId.

### DailyGoal
Representa meta diária do usuário.

Campos:
- id;
- userId;
- targetActivities;
- date;
- completedActivities;

### UserProgressSnapshot
Read model de progresso consolidado.

Campos:
- userId;
- streakDays;
- skillProgress;
- weeklySummary;
- totalActivities.

## Value Objects
- ActivityType;
- SkillType;
- ProgressScore;
- Streak;
- GoalStatus.

## Regras
1. Atividade concluída deve pertencer a um usuário.
2. Atividade deve impactar pelo menos uma habilidade.
3. Streak depende de atividade concluída no dia.
4. Meta diária depende da quantidade de atividades concluídas.
5. Progresso deve ser derivado de eventos/atividades, não input manual solto.

## Eventos
- LearningActivityRegistered;
- DailyGoalCompleted;
- StreakUpdated.
