# Plan 009 — First Version Hardening

## Objetivo

Revisar, estabilizar e preparar a primeira versão do Talanguage para uso real ou demonstração consistente.

Esta fase não deve criar novas features. Ela deve corrigir lacunas, remover mocks indevidos, estabilizar integração e validar qualidade mínima.

## Premissas

- As features principais já foram implementadas ou parcialmente integradas.
- O front-end já deve estar conectado ao backend nos fluxos principais.
- Mocks podem existir apenas quando forem explícitos e aceitos.
- O objetivo é reduzir risco técnico e aumentar coerência de produto.

## Documentos obrigatórios

- `docs/01-ai-contract/definition-of-done.md`
- `docs/01-ai-contract/forbidden-decisions.md`
- `docs/02-architecture/testing-strategy.md`
- `docs/02-architecture/observability-rules.md`
- `docs/08-indexes/feature-to-docs-matrix.md`
- `docs/08-indexes/route-api-component-matrix.md`
- `docs/06-skills/fullstack/revisar-feature-completa/SKILL.md`
- `docs/06-skills/governance/validar-aderencia-arquitetural/SKILL.md`
- `docs/06-skills/governance/validar-economia-de-tokens/SKILL.md`

## Ordem de execução

1. Revisar todas as features contra suas specs.
2. Validar rotas, endpoints e componentes pela matriz.
3. Identificar mocks ainda existentes.
4. Classificar mocks como aceitos, temporários ou indevidos.
5. Remover mocks indevidos.
6. Validar testes unitários principais.
7. Validar testes de API principais.
8. Validar testes frontend críticos.
9. Revisar estados de loading, empty e error.
10. Revisar responsividade.
11. Revisar mensagens de UX writing.
12. Validar segurança básica de autenticação.
13. Validar logs e observabilidade mínima.
14. Gerar lista objetiva de pendências.

## Entregáveis esperados

- Relatório de hardening.
- Lista de mocks restantes.
- Lista de pendências reais.
- Correções de inconsistência arquitetural.
- Testes principais passando.
- Primeira versão demonstrável.

## Proibido

- Criar feature nova.
- Reescrever arquitetura sem ADR.
- Adicionar dependência para resolver problema pequeno.
- Esconder pendências.
- Deixar mock parecendo dado real.
- Fazer polimento visual que não melhore clareza ou usabilidade.

## Critério de pronto

A primeira versão estará endurecida quando:

- fluxos principais funcionarem;
- front-end e backend estiverem integrados onde previsto;
- mocks restantes forem explícitos;
- testes críticos passarem;
- as features respeitarem suas specs;
- a aplicação puder ser demonstrada sem explicar desculpas técnicas a cada tela.
