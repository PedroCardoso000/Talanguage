# Skill — Validar Escopo da Primeira Versão

## Objetivo

Validar se uma tarefa, feature ou alteração pertence à primeira versão do Talanguage.

## Quando usar

Use antes de criar feature, endpoint, tela, dependência ou integração relevante.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/01-ai-contract/token-economy-rules.md
- docs/01-ai-contract/definition-of-done.md
- docs/08-indexes/reading-order.md
- docs/00-product/first-version-scope.md
- docs/00-product/out-of-scope.md

## Processo

1. Comparar solicitação com first-version-scope.
2. Verificar out-of-scope.
3. Classificar como permitido, proibido ou pendente de decisão.
4. Se permitido, indicar docs necessários.
5. Se proibido, recomendar alternativa dentro da primeira versão.
6. Se pendente, solicitar decisão explícita.

## Proibido

- Implementar item fora de escopo por parecer útil.
- Transformar primeira versão em produto final completo.
- Adicionar complexidade sem validação.

## Critério de pronto

- Classificação de escopo clara.
- Decisão registrada.
- Ação recomendada respeita primeira versão.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
