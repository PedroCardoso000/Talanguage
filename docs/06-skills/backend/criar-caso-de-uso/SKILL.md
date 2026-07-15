# Skill — Criar Caso de Uso

## Objetivo

Criar casos de uso na camada de aplicação para orquestrar regras de domínio, portas e fluxos de entrada/saída.

## Quando usar

Use para qualquer operação relevante acionada por API, job, evento ou interação do usuário.

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
- docs/05-features/{feature}/api-spec.md
- docs/05-features/{feature}/test-spec.md

## Processo

1. Identificar a operação na backend-spec.
2. Definir input e output do caso de uso.
3. Validar permissões e pré-condições de aplicação.
4. Carregar entidades por repositórios/portas.
5. Executar comportamento no domínio.
6. Persistir alterações por interfaces.
7. Retornar DTO/application result sem expor entidade indevidamente.
8. Criar testes unitários com repositórios fake ou mocks simples.

## Proibido

- Colocar regra de negócio no controller.
- Acessar ORM diretamente no caso de uso se a arquitetura proibir.
- Retornar entidade de domínio diretamente para HTTP.
- Criar caso de uso genérico demais.
- Ignorar cenários de erro previstos na spec.

## Critério de pronto

- Caso de uso implementa uma intenção clara.
- Dependências são interfaces/portas.
- Fluxos de sucesso e erro estão testados.
- Controller fica fino.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
