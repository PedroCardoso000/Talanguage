# Skill — Criar Entidade

## Objetivo

Criar ou ajustar uma entidade de domínio do Talanguage mantendo comportamento, identidade e invariantes dentro da camada de domínio.

## Quando usar

Use quando uma feature exigir uma entidade com ciclo de vida próprio, identidade e regras relevantes de negócio.

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

1. Confirmar que a entidade está prevista na domain-spec da feature ou em docs/04-domain/entities.md.
2. Definir identidade, propriedades mínimas e invariantes.
3. Implementar métodos de comportamento em vez de setters anêmicos quando houver regra de negócio.
4. Evitar dependência de framework, banco, HTTP ou UI.
5. Adicionar testes unitários cobrindo invariantes e transições de estado.
6. Atualizar documentação da feature se algum campo obrigatório for refinado.

## Proibido

- Criar entidade baseada apenas em tabela ou formulário.
- Incluir anotação de ORM dentro do domínio, salvo decisão explícita.
- Colocar validação de request HTTP dentro da entidade.
- Criar entidade genérica sem uso real.
- Criar propriedade pública mutável sem necessidade.

## Critério de pronto

- Entidade representa conceito real do domínio.
- Invariantes principais estão protegidas.
- Não há dependência de infraestrutura.
- Testes unitários cobrem criação e regras relevantes.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
