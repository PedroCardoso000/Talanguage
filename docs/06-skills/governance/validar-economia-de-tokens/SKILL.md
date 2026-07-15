# Skill — Validar Economia de Tokens

## Objetivo

Reduzir contexto carregado e orientar a IA a ler apenas documentos necessários para a tarefa atual.

## Quando usar

Use antes de prompts grandes, criação de feature ou execução em ferramentas de IA com limite de contexto.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/01-ai-contract/token-economy-rules.md
- docs/01-ai-contract/definition-of-done.md
- docs/08-indexes/reading-order.md

## Processo

1. Identificar tipo da tarefa.
2. Consultar reading-order.
3. Listar somente documentos obrigatórios.
4. Remover documentos informativos que não alteram decisão.
5. Preferir template específico em vez de documentação ampla.
6. Incluir no prompt apenas paths necessários e objetivo.
7. Bloquear leitura indiscriminada de todo o repositório quando não for necessário.

## Proibido

- Mandar a IA ler todos os docs por padrão.
- Incluir histórico longo de conversa no prompt.
- Repetir contexto já documentado.
- Usar prompt gigante quando existe skill/template.

## Critério de pronto

- Prompt final tem documentos mínimos.
- Contexto carregado é suficiente e não excessivo.
- Tarefa fica rastreável por docs e skills.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
