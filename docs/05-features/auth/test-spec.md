<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Test Spec — Auth

## Backend unit tests
- registra usuário válido;
- rejeita e-mail inválido;
- rejeita e-mail duplicado;
- rejeita senha fraca;
- autentica credenciais válidas;
- rejeita credenciais inválidas;
- não retorna senha/hash.

## API tests
- POST /api/auth/register retorna 201;
- POST /api/auth/login retorna 200;
- GET /api/auth/me retorna 200 autenticado;
- GET /api/auth/me retorna 401 sem token;
- e-mail duplicado retorna 409.

## Frontend tests
- renderiza login;
- renderiza cadastro;
- envia dados ao client API;
- exibe erro de credenciais;
- redireciona após login;
- bloqueia rota privada sem autenticação.
