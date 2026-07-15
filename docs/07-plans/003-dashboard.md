# Plan 003 — Dashboard

## Objetivo

Transformar o dashboard mockado em uma visão funcional da rotina do usuário, preservando a UI existente e conectando progressivamente dados reais.

## Premissas

- O dashboard visual já pode existir.
- Métricas atuais podem estar mockadas.
- O backend deve fornecer dados agregados simples.
- O dashboard não deve virar uma tela densa ou analítica demais.

## Documentos obrigatórios

- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/03-design-system/layout-rules.md`
- `docs/03-design-system/ui-components.md`
- `docs/05-features/dashboard/feature-spec.md`
- `docs/05-features/dashboard/domain-spec.md`
- `docs/05-features/dashboard/api-spec.md`
- `docs/05-features/dashboard/backend-spec.md`
- `docs/05-features/dashboard/frontend-spec.md`
- `docs/05-features/dashboard/test-spec.md`
- `docs/06-skills/fullstack/integrar-frontend-backend/SKILL.md`

## Ordem de execução

1. Inventariar cards e dados mockados do dashboard atual.
2. Definir quais dados vêm de API e quais permanecem locais temporariamente.
3. Criar contrato de API para resumo da rotina.
4. Criar caso de uso para carregar overview do usuário.
5. Criar endpoint de dashboard overview.
6. Criar testes do caso de uso e endpoint.
7. Criar client API no front-end.
8. Substituir mocks por dados reais gradualmente.
9. Preservar layout e componentes existentes sempre que possível.
10. Validar estados de loading, empty e error.

## Entregáveis esperados

- Dashboard integrado ao backend.
- Cards principais alimentados por API.
- Fallbacks definidos para ausência de dados.
- UI consistente com design system.
- Testes dos fluxos principais.

## Proibido

- Colocar cálculo de progresso complexo no front-end.
- Criar métricas não especificadas.
- Adicionar gráficos pesados sem necessidade.
- Recriar a tela se a UI existente for aproveitável.
- Misturar dados reais e mocks sem identificar fonte.

## Critério de pronto

O dashboard estará pronto quando:

- carregar dados do usuário autenticado;
- exibir rotina, metas e progresso resumido;
- manter UI limpa;
- tratar ausência de dados;
- possuir contrato de API estável;
- não depender de mocks escondidos nos componentes.
