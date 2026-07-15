<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Frontend Spec — Dashboard

## Rota
`/dashboard`

## Contexto atual
Tela já existe com cards mockados. Preservar layout e conectar ao endpoint `/api/dashboard/summary`.

## Componentes esperados
- DashboardPage;
- DailyGoalCard;
- SkillProgressCard;
- RecentActivitiesList;
- NextActionCard;
- ModuleShortcutGrid;
- EmptyDashboardState.

## Client API
`dashboardApiClient.getSummary()`.

## Estado de UI
- loading;
- loaded;
- empty;
- error.

## Regras
- não calcular progresso complexo no componente;
- tratar ausência de atividades como estado vazio positivo;
- manter cards com pouca informação;
- atalhos devem respeitar rotas existentes.
