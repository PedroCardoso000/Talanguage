# Skill — Criar Estado de UI

## Objetivo

Criar estado de UI local ou compartilhado apenas quando necessário, sem transformar o frontend em fonte de regra de negócio.

## Quando usar

Use para controlar fluxo visual, seleção, filtros, sessão de tela, loading e estados transitórios.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/02-architecture/frontend-architecture.md
- docs/03-design-system/ui-components.md
- docs/03-design-system/layout-rules.md
- docs/05-features/{feature}/frontend-spec.md

## Processo

1. Identificar se o estado é local, compartilhado ou servidor.
2. Preferir estado local quando possível.
3. Usar cache/query apenas quando houver API real e necessidade clara.
4. Não duplicar dado de servidor sem motivo.
5. Separar estado visual de regra de negócio.
6. Documentar estado relevante na frontend-spec se necessário.

## Proibido

- Criar store global por conveniência.
- Usar Redux sem autorização.
- Guardar regra de domínio no frontend.
- Duplicar dados de API em múltiplos lugares.

## Critério de pronto

- Estado tem dono claro.
- Solução é simples e reversível.
- Não há regra de negócio indevida no frontend.
- Tela permanece legível.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
