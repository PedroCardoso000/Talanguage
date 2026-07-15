<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Test Spec — Community

## Backend unit tests
- lista grupos ativos;
- filtra por idioma;
- lista parceiros;
- registra interesse;
- evita interesse duplicado;
- não expõe dados sensíveis.

## API tests
- GET /groups retorna 200;
- GET /partners retorna 200;
- POST /interests retorna 201;
- sem auth retorna 401.

## Frontend tests
- renderiza grupos;
- renderiza parceiros;
- aplica filtro;
- registra interesse;
- exibe estado vazio.
