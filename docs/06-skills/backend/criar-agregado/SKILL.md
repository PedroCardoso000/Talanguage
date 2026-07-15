# Skill — Criar Agregado

## Objetivo

Criar ou ajustar agregado garantindo consistência transacional e proteção das regras internas do domínio.

## Quando usar

Use quando uma entidade raiz coordenar outras entidades ou objetos dependentes.

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

## Processo

1. Confirmar a raiz do agregado em docs/04-domain/aggregates.md ou na domain-spec.
2. Definir quais objetos pertencem ao limite de consistência.
3. Proteger alterações internas por métodos da raiz.
4. Definir eventos de domínio relevantes, se aplicável.
5. Evitar navegação livre entre agregados.
6. Criar testes cobrindo regras e transições do agregado.

## Proibido

- Criar agregado gigante.
- Permitir alteração direta de entidades internas fora da raiz.
- Misturar agregados por conveniência de consulta.
- Criar transação atravessando múltiplos agregados sem decisão explícita.

## Critério de pronto

- Raiz do agregado está clara.
- Regras internas são aplicadas pela raiz.
- Limite transacional está explícito.
- Testes cobrem comportamentos críticos.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
