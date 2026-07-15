<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Test Spec — Writing Review

## Backend unit tests
- lista desafios ativos;
- rejeita resposta vazia;
- rejeita challenge inexistente;
- cria submissão;
- gera feedback;
- registra atividade após revisão.

## API tests
- GET /challenges retorna 200;
- POST /submissions retorna 201;
- resposta vazia retorna 400;
- endpoints sem auth retornam 401.

## Frontend tests
- renderiza desafios;
- permite digitar resposta;
- envia resposta;
- exibe feedback;
- exibe erro de validação.
