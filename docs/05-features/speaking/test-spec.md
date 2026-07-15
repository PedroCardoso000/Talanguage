<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Test Spec — Speaking Practice

## Backend unit tests
- lista temas;
- inicia sessão válida;
- rejeita idioma inválido;
- rejeita tema inexistente;
- registra resposta em sessão ativa;
- rejeita resposta em sessão finalizada;
- finaliza sessão;
- registra atividade de aprendizagem ao finalizar.

## API tests
- GET /topics retorna 200;
- POST /sessions retorna 201;
- POST /responses retorna 200;
- POST /finish retorna 200;
- endpoints protegidos retornam 401 sem auth.

## Frontend tests
- renderiza seleção inicial;
- carrega temas;
- inicia sessão;
- envia resposta;
- finaliza sessão;
- exibe resumo.
