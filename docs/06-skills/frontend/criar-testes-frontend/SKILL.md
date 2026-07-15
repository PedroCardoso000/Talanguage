# Skill — Criar Testes Frontend

## Objetivo

Criar testes de renderização, interação e integração leve da UI com client API mockado.

## Quando usar

Use quando criar ou alterar página, formulário, client API ou fluxo de usuário.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/02-architecture/frontend-architecture.md
- docs/03-design-system/ui-components.md
- docs/03-design-system/layout-rules.md
- docs/05-features/{feature}/frontend-spec.md
- docs/02-architecture/testing-strategy.md
- docs/05-features/{feature}/test-spec.md

## Processo

1. Ler test-spec da feature.
2. Priorizar comportamento do usuário.
3. Mockar client API, não detalhes internos de fetch.
4. Testar renderização inicial, interação principal, sucesso e erro.
5. Evitar snapshots frágeis.
6. Garantir que testes não dependam de backend real.

## Proibido

- Testar detalhe de CSS sem necessidade.
- Criar snapshot gigante.
- Chamar API real.
- Testar implementação interna em vez de comportamento.

## Critério de pronto

- Fluxo principal testado.
- Erro e loading testados quando aplicável.
- Testes são legíveis e estáveis.
- Não dependem de backend real.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
