# Skill — Criar Endpoint

## Objetivo

Criar endpoint HTTP fino, aderente à api-spec e conectado ao caso de uso correto.

## Quando usar

Use quando uma feature exigir contrato HTTP entre frontend e backend.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/02-architecture/backend-architecture.md
- docs/02-architecture/clean-architecture-rules.md
- docs/04-domain/language-learning-domain.md
- docs/04-domain/business-rules.md
- docs/05-features/{feature}/domain-spec.md
- docs/05-features/{feature}/backend-spec.md
- docs/02-architecture/api-contract-rules.md
- docs/05-features/{feature}/api-spec.md

## Processo

1. Ler a api-spec da feature.
2. Criar DTOs de request e response.
3. Validar request no limite HTTP.
4. Chamar exatamente o caso de uso correspondente.
5. Mapear resultado de aplicação para status HTTP.
6. Padronizar erros conforme api-contract-rules.
7. Criar ou atualizar testes de API.

## Proibido

- Criar endpoint não documentado.
- Colocar regra de domínio no controller.
- Retornar entidade interna.
- Usar status HTTP incoerente.
- Alterar contrato sem atualizar api-spec.

## Critério de pronto

- Endpoint segue rota, método e payload da api-spec.
- Controller está fino.
- Erros previstos estão mapeados.
- Teste de API cobre sucesso e falhas principais.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
