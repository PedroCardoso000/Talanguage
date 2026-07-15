# Skill — Criar Migration

## Objetivo

Criar migration de banco apenas quando a persistência real da feature estiver aprovada e documentada.

## Quando usar

Use quando database-rules e backend-spec indicarem criação ou alteração de tabelas.

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
- docs/02-architecture/database-rules.md

## Processo

1. Confirmar que persistência real é necessária.
2. Identificar entidade/agregado e campos persistidos.
3. Criar migration mínima e reversível.
4. Definir constraints, índices e chaves de forma explícita.
5. Evitar dados seed desnecessários.
6. Atualizar documentação se o modelo persistente divergir do domínio.
7. Criar teste de integração quando aplicável.

## Proibido

- Criar migration para dado ainda mockado.
- Criar tabela sem relação com feature documentada.
- Adicionar coluna sem regra de uso.
- Armazenar segredo ou dado sensível indevidamente.

## Critério de pronto

- Migration é mínima.
- Estrutura respeita database-rules.
- Rollback ou reversibilidade está considerada.
- Testes/validação de integração foram feitos quando aplicável.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
