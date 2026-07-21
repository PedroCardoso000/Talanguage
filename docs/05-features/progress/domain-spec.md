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

6. O dia de aprendizagem usa UTC na primeira versão, enquanto o perfil não possuir timezone configurável.
7. Uma atividade concluída conta no máximo uma vez no streak por dia, independentemente do volume no mesmo dia.
8. `sourceId` identifica a conclusão na origem e torna seu registro idempotente por usuário e tipo.
9. Atividades canceladas, incompletas ou que falharam na validação não geram `LearningActivity`.

## Eventos
- LearningActivityRegistered;
- DailyGoalCompleted;
- StreakUpdated.
