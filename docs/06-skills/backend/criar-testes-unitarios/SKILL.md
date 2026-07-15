# Skill — Criar Testes Unitários Backend

## Objetivo

Criar testes unitários para domínio e casos de uso, priorizando comportamento e regras de negócio.

## Quando usar

Use sempre que criar ou alterar entidade, value object, agregado ou caso de uso.

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
- docs/02-architecture/testing-strategy.md
- docs/05-features/{feature}/test-spec.md

## Processo

1. Ler test-spec da feature.
2. Priorizar regras de domínio e casos de uso.
3. Criar testes com nomes comportamentais.
4. Usar fakes simples para portas/repositórios.
5. Cobrir sucesso, validação e erros relevantes.
6. Evitar testar detalhes de framework.
7. Rodar testes ou informar limitação se não for possível.

## Proibido

- Testar implementação interna sem valor.
- Depender de banco real em teste unitário.
- Criar teste frágil acoplado a ordem interna.
- Ignorar cenários negativos documentados.

## Critério de pronto

- Regras principais têm teste.
- Casos de erro relevantes têm teste.
- Testes são rápidos e isolados.
- Nomes descrevem comportamento esperado.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
