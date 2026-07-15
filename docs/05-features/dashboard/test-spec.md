<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Test Spec — Dashboard

## Backend unit tests
- monta resumo para usuário com atividades;
- monta estado inicial para usuário novo;
- calcula meta diária concluída/parcial;
- não mistura dados de usuários.

## API tests
- GET /api/dashboard/summary retorna 200 autenticado;
- retorna 401 sem autenticação;
- payload contém campos esperados.

## Frontend tests
- renderiza cards principais;
- exibe estado carregando;
- exibe estado vazio;
- exibe atividades recentes;
- navega por atalhos.
