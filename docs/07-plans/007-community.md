# Plan 007 — Community

## Objetivo

Implementar a área de comunidade da primeira versão como um espaço simples para visualizar grupos, parceiros ou salas de prática.

## Premissas

- A primeira versão não deve virar rede social completa.
- Chat em tempo real não é obrigatório sem spec explícita.
- A UI existente deve ser reaproveitada.
- Dados podem começar mockados, mas devem ter caminho para API real.

## Documentos obrigatórios

- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/00-product/out-of-scope.md`
- `docs/05-features/community/feature-spec.md`
- `docs/05-features/community/domain-spec.md`
- `docs/05-features/community/api-spec.md`
- `docs/05-features/community/backend-spec.md`
- `docs/05-features/community/frontend-spec.md`
- `docs/05-features/community/test-spec.md`
- `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## Ordem de execução

1. Validar escopo da comunidade.
2. Mapear UI e mocks existentes.
3. Definir se a primeira versão usa grupos, parceiros, salas ou combinação simples.
4. Criar domínio mínimo da comunidade.
5. Criar use case de listar grupos ou parceiros.
6. Criar use case de visualizar detalhes, se aplicável.
7. Criar endpoints e DTOs.
8. Criar testes principais.
9. Criar client API no front-end.
10. Integrar cards/listas existentes com API.
11. Validar estados vazio, carregando e erro.

## Entregáveis esperados

- Área de comunidade funcional.
- Listagem de grupos/parceiros/salas conforme spec.
- Front-end integrado com API.
- Escopo limitado e claro.
- Testes principais.

## Proibido

- Criar rede social completa.
- Criar chat em tempo real sem spec.
- Criar notificações em tempo real sem spec.
- Criar feed infinito.
- Criar sistema de amizade complexo.
- Criar moderação avançada sem decisão explícita.

## Critério de pronto

A feature estará pronta quando:

- o usuário visualizar opções de prática com outras pessoas;
- a UI carregar dados via client API;
- o backend respeitar o escopo simples;
- a feature não ultrapassar a primeira versão;
- os testes principais passarem.
