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

- primeira atividade inicia streak em 1;
- múltiplas atividades no mesmo dia não incrementam streak;
- atividade no dia seguinte incrementa streak;
- sem atividade hoje preserva a sequência de ontem sem incrementar;
- dia completo perdido quebra current streak e preserva longest streak;
- fronteira UTC não muda o dia conforme timezone do host;
- registro repetido da mesma origem permanece único sob concorrência.

## API tests
- GET /summary retorna 200;
- GET /activities retorna 200;
- GET /weekly-summary retorna 200;
- sem auth retorna 401.
- dashboard e progress retornam o mesmo streak.

## Frontend tests
- renderiza resumo;
- renderiza progresso por habilidade;
- renderiza histórico;
- exibe estado vazio;
- trata erro de API.
