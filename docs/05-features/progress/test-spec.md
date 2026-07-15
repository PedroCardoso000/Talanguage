<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Test Spec — Progress

## Backend unit tests
- registra atividade;
- calcula meta diária;
- calcula streak;
- calcula progresso por habilidade;
- retorna estado inicial para usuário novo;
- não mistura atividades de usuários.

## API tests
- GET /summary retorna 200;
- GET /activities retorna 200;
- GET /weekly-summary retorna 200;
- sem auth retorna 401.

## Frontend tests
- renderiza resumo;
- renderiza progresso por habilidade;
- renderiza histórico;
- exibe estado vazio;
- trata erro de API.
