# Skill — Criar Value Object

## Objetivo

Criar value objects para representar conceitos imutáveis, validados e sem identidade própria.

## Quando usar

Use quando um valor possuir regra própria, formato controlado ou significado de domínio.

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

1. Identificar o valor na domain-spec ou em docs/04-domain/value-objects.md.
2. Definir formato, valores permitidos e regras de validação.
3. Implementar imutabilidade.
4. Implementar igualdade por valor quando a linguagem exigir.
5. Adicionar testes para valores válidos e inválidos.
6. Usar o value object nas entidades e casos de uso quando fizer sentido.

## Proibido

- Usar string solta para conceito com regra importante.
- Criar value object para valor trivial sem regra.
- Permitir estado mutável.
- Acoplar value object a banco, framework ou UI.

## Critério de pronto

- Valor possui validação explícita.
- Objeto é imutável.
- Testes cobrem casos válidos e inválidos.
- Uso reduz ambiguidade no domínio.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
