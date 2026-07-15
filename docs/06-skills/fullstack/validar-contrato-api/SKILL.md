# Skill — Validar Contrato API

## Objetivo

Validar consistência entre api-spec, backend, client API e uso no frontend.

## Quando usar

Use antes de concluir uma feature integrada ou ao corrigir incompatibilidade front-back.

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

1. Comparar método, rota, request e response da api-spec.
2. Verificar controller/backend.
3. Verificar DTOs.
4. Verificar client API frontend.
5. Verificar telas/hooks que consomem o client.
6. Checar status HTTP e formato de erro.
7. Registrar divergências e corrigir se estiver no escopo.

## Proibido

- Alterar contrato sem atualizar spec.
- Corrigir só frontend quando backend está errado.
- Ignorar formato de erro.
- Aceitar campo fantasma não documentado.

## Critério de pronto

- Spec, backend e frontend usam o mesmo contrato.
- Erros seguem padrão.
- Divergências foram corrigidas ou listadas.
- Testes protegem contrato principal.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
