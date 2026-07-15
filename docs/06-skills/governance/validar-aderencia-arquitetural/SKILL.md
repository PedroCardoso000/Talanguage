# Skill — Validar Aderência Arquitetural

## Objetivo

Verificar se uma implementação respeita a arquitetura definida para frontend, backend, API e domínio.

## Quando usar

Use após implementar feature ou antes de aceitar PR/alteração gerada por IA.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/01-ai-contract/token-economy-rules.md
- docs/01-ai-contract/definition-of-done.md
- docs/08-indexes/reading-order.md
- docs/02-architecture/system-overview.md
- docs/02-architecture/frontend-architecture.md
- docs/02-architecture/backend-architecture.md
- docs/02-architecture/clean-architecture-rules.md

## Processo

1. Verificar separação de camadas.
2. Verificar dependências permitidas.
3. Verificar localização de regras de negócio.
4. Verificar controllers/endpoints.
5. Verificar organização frontend.
6. Verificar se mudança exigiria ADR.
7. Listar violações por severidade.

## Proibido

- Aprovar mistura de domínio com infraestrutura.
- Aceitar regra de negócio no frontend.
- Ignorar dependência proibida.
- Normalizar arquitetura diferente por feature.

## Critério de pronto

- Violação crítica identificada ou descartada.
- Correções recomendadas são objetivas.
- Arquitetura permanece consistente.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
