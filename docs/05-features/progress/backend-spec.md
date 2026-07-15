<!--
Talanguage - Feature Specification
Contexto: o front-end ja possui UI mockada estruturada. O backend ainda nao existe.
Objetivo: orientar a transicao de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisoes fora da arquitetura documentada.
-->

# Backend Spec - Progress

## Casos de uso
- RegisterLearningActivityUseCase;
- GetProgressSummaryUseCase;
- GetLearningActivitiesUseCase;
- GetWeeklyProgressSummaryUseCase.

## Portas/interfaces
- LearningActivityRepository;
- DailyGoalRepository;
- ProgressCalculator;
- ProgressReadService.

## Regras
- modulos como Speaking, Writing, Flashcards e Mock Test devem registrar atividades;
- calculo de progresso fica no backend;
- dashboard pode reutilizar read service de progresso;
- evitar calculo duplicado no front-end;
- `summary` deve expor contadores agregados reais por tipo de atividade;
- `weekly-summary` deve considerar apenas a janela semanal corrente.

## Infraestrutura
- persistencia de atividades;
- query service para resumos;
- indices por `userId` e `completedAt` quando houver banco.
