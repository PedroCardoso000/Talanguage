<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Test Spec — Flashcards

## Backend unit tests
- cria card válido;
- rejeita frente vazia;
- rejeita verso vazio;
- lista cards do usuário;
- registra revisão;
- recalcula próxima revisão;
- impede revisar card de outro usuário.

## API tests
- GET /flashcards retorna 200;
- POST /flashcards retorna 201;
- POST /{id}/review retorna 200;
- dados inválidos retornam 400;
- sem auth retorna 401.

## Frontend tests
- renderiza lista;
- cria card;
- mostra estado vazio;
- revela resposta;
- envia avaliação de revisão.
