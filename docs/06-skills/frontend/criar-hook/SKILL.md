# Skill — Criar Hook Frontend

## Objetivo

Criar hook para encapsular estado, chamadas de API ou comportamento reutilizável de UI.

## Quando usar

Use quando lógica de tela começar a se repetir ou poluir componente visual.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/02-architecture/frontend-architecture.md
- docs/03-design-system/ui-components.md
- docs/03-design-system/layout-rules.md
- docs/05-features/{feature}/frontend-spec.md

## Processo

1. Definir responsabilidade do hook.
2. Verificar se já existe hook similar.
3. Manter contrato de entrada/saída simples.
4. Encapsular estados de loading, error e data quando fizer sentido.
5. Não incluir regra de domínio que pertença ao backend.
6. Criar teste quando a lógica for relevante.

## Proibido

- Criar hook para uma linha trivial.
- Misturar múltiplas responsabilidades.
- Acoplar hook a componente visual específico sem necessidade.
- Esconder mock não documentado dentro do hook.

## Critério de pronto

- Hook reduz complexidade da tela.
- Contrato é claro.
- Não contém regra de domínio.
- Pode ser reutilizado ou removido facilmente.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
