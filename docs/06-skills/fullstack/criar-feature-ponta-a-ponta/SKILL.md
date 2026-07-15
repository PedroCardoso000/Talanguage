# Skill — Criar Feature Ponta a Ponta

## Objetivo

Criar uma feature full-stack completa, conectando domínio, aplicação, infraestrutura, API, frontend e testes.

## Quando usar

Use quando a tarefa envolver comportamento novo ou alteração relevante que atravessa backend e frontend.

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
- docs/08-indexes/reading-order.md
- docs/08-indexes/feature-to-docs-matrix.md

## Processo

1. Validar se a feature está dentro da primeira versão.
2. Ler somente os documentos indicados para a feature.
3. Identificar domínio, entidades, value objects e regras.
4. Criar ou ajustar casos de uso.
5. Criar portas, repositórios e infraestrutura mínima.
6. Criar DTOs e endpoints conforme api-spec.
7. Criar testes unitários e de API.
8. Criar ou ajustar client API no frontend.
9. Conectar tela existente ou criar tela conforme frontend-spec.
10. Remover ou isolar mock substituído por backend real.
11. Criar testes frontend quando aplicável.
12. Executar validações de escopo, arquitetura, contratos e dependências.

## Proibido

- Criar feature fora da spec.
- Recriar front-end mockado sem necessidade.
- Pular backend e manter regra no frontend.
- Criar endpoint sem caso de uso.
- Alterar arquitetura global sem ADR.
- Adicionar dependência sem autorização.

## Critério de pronto

- Feature entrega fluxo utilizável.
- Backend e frontend seguem contrato.
- Mocks foram mantidos apenas onde documentado.
- Testes principais existem.
- Resumo final lista decisões e pendências.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
