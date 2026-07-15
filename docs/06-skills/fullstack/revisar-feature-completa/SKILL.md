# Skill — Revisar Feature Completa

## Objetivo

Revisar uma feature ponta a ponta antes de considerá-la pronta.

## Quando usar

Use ao final de implementação ou refatoração full-stack.

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
- docs/01-ai-contract/definition-of-done.md

## Processo

1. Validar escopo da primeira versão.
2. Validar domínio e casos de uso.
3. Validar endpoints e contratos.
4. Validar integração frontend-backend.
5. Validar uso ou remoção de mocks.
6. Validar testes.
7. Validar aderência visual.
8. Validar dependências e proibições.
9. Listar pendências objetivamente.

## Proibido

- Aprovar feature sem teste mínimo.
- Ignorar mocks residuais.
- Aceitar regra de negócio no frontend.
- Aceitar controller gordo.

## Critério de pronto

- Feature cumpre definition-of-done.
- Pendências estão explícitas.
- Não há violação arquitetural crítica.
- Usuário consegue executar o fluxo principal.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
