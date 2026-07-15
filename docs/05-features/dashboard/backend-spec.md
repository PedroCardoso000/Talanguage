<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Backend Spec — Dashboard

## Casos de uso

### GetDashboardSummaryUseCase
Entrada:
- userId.

Saída:
- UserLearningSummary.

Regras:
- buscar resumo de atividades;
- calcular metas do dia;
- calcular sequência de dias;
- montar progresso por habilidade;
- sugerir próxima ação simples.

## Portas/interfaces
- LearningActivityRepository;
- DailyGoalRepository;
- ProgressQueryService;
- DashboardSummaryReadService.

## Infraestrutura
Pode iniciar com query service simples e evoluir para read model dedicado.

## Proibido
- criar regras de escrita/fala dentro do dashboard;
- duplicar cálculos complexos já pertencentes ao contexto de progresso;
- retornar payload gigante.
