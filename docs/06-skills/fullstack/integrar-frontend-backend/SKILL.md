# Skill — Integrar Frontend e Backend

## Objetivo

Substituir uso de mock por integração real com backend preservando UI existente e contrato da API.

## Quando usar

Use quando o frontend já existe mockado e o backend ou endpoint está pronto para integração.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/02-architecture/system-overview.md
- docs/02-architecture/backend-architecture.md
- docs/02-architecture/frontend-architecture.md
- docs/02-architecture/clean-architecture-rules.md
- docs/02-architecture/api-contract-rules.md
- docs/05-features/{feature}/feature-spec.md
- docs/05-features/{feature}/domain-spec.md
- docs/05-features/{feature}/backend-spec.md
- docs/05-features/{feature}/api-spec.md
- docs/05-features/{feature}/frontend-spec.md
- docs/05-features/{feature}/test-spec.md

## Processo

1. Confirmar endpoint na api-spec.
2. Confirmar client API esperado na frontend-spec.
3. Criar ou ajustar client API.
4. Substituir mock direto por chamada controlada.
5. Preservar componentes visuais existentes.
6. Adicionar estados de loading, error e empty.
7. Validar payload de request/response.
8. Atualizar testes frontend e API quando necessário.

## Proibido

- Reescrever tela inteira sem necessidade.
- Manter mock escondido depois da integração.
- Divergir da api-spec.
- Tratar erro técnico cru na UI.

## Critério de pronto

- Tela consome backend via client API.
- Mock removido ou isolado explicitamente.
- Contrato respeitado.
- Fluxos de loading/sucesso/erro funcionam.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
