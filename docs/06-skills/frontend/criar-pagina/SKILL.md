# Skill — Criar Página Frontend

## Objetivo

Criar ou ajustar página do Talanguage respeitando rotas, layout existente, design system e frontend-spec.

## Quando usar

Use quando uma feature exigir tela, rota ou composição visual de componentes.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/02-architecture/frontend-architecture.md
- docs/03-design-system/ui-components.md
- docs/03-design-system/layout-rules.md
- docs/05-features/{feature}/frontend-spec.md
- docs/08-indexes/route-api-component-matrix.md

## Processo

1. Confirmar rota e página na matriz de rotas.
2. Ler frontend-spec da feature.
3. Reutilizar layout, sidebar, header e componentes existentes.
4. Criar apenas componentes necessários.
5. Consumir dados via client API ou adapter de mock documentado.
6. Evitar recriar UI já existente.
7. Adicionar estados de loading, empty e error quando aplicável.
8. Criar testes de renderização/interação quando previsto.

## Proibido

- Criar rota não documentada.
- Recriar layout global.
- Colocar regra de domínio na página.
- Inserir mock diretamente no JSX.
- Adicionar biblioteca visual sem autorização.

## Critério de pronto

- Página segue frontend-spec.
- Rota está correta.
- UI usa componentes existentes quando possível.
- Estados principais foram tratados.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
