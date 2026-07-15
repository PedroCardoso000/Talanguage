# Plan 008 — Progress

## Objetivo

Implementar a área de progresso da primeira versão, permitindo que o usuário acompanhe evolução, metas, sequência e desempenho por habilidade.

## Premissas

- O progresso deve ser simples, mensurável e útil.
- Métricas complexas devem ser evitadas sem base real de dados.
- O front-end pode já ter cards e gráficos mockados.
- A primeira versão deve priorizar clareza, não sofisticação analítica.

## Documentos obrigatórios

- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/02-architecture/api-contract-rules.md`
- `docs/04-domain/business-rules.md`
- `docs/05-features/progress/feature-spec.md`
- `docs/05-features/progress/domain-spec.md`
- `docs/05-features/progress/api-spec.md`
- `docs/05-features/progress/backend-spec.md`
- `docs/05-features/progress/frontend-spec.md`
- `docs/05-features/progress/test-spec.md`
- `docs/06-skills/fullstack/integrar-frontend-backend/SKILL.md`

## Ordem de execução

1. Validar métricas permitidas na primeira versão.
2. Mapear cards, gráficos e mocks existentes.
3. Definir origem de dados de progresso.
4. Criar domínio de meta, atividade e progresso, se aplicável.
5. Criar use case de obter resumo de progresso.
6. Criar use case de obter progresso por habilidade, se especificado.
7. Criar endpoints e DTOs.
8. Criar testes de cálculo ou agregação.
9. Criar client API no front-end.
10. Substituir mocks por dados reais quando possível.
11. Validar estados vazio, parcial e completo.

## Entregáveis esperados

- Tela de progresso integrada.
- Métricas simples e rastreáveis.
- Progresso por habilidade, se especificado.
- Sequência de dias ou metas, se especificado.
- Testes para regras de cálculo.

## Proibido

- Criar métrica sem origem clara.
- Criar cálculo complexo no front-end.
- Criar gráfico pesado sem necessidade.
- Exibir precisão falsa.
- Criar ranking competitivo sem spec.

## Critério de pronto

A feature estará pronta quando:

- o usuário visualizar progresso de forma clara;
- métricas vierem de API ou fonte controlada;
- cálculos relevantes forem testados;
- a UI não prometer precisão inexistente;
- mocks restantes forem explícitos.
