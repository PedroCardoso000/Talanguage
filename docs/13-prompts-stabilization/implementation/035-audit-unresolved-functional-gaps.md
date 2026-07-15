# 035 — Audit Unresolved Functional Gaps

## Objetivo
Mapear o estado real após os prompts Spring Boot fix e transformar os problemas restantes em checklist técnico executável.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/01-ai-contract/token-economy-rules.md`
3. `docs/02-architecture/system-overview.md`
4. `docs/02-architecture/backend-architecture.md`
5. `docs/02-architecture/frontend-architecture.md`
6. `docs/07-plans/010-frontend-alignment.md`
7. `docs/12-prompts-production-springboot-fix/README.md`, se existir

## Problemas conhecidos
- Textos do sistema sem pontuação ou com frases ruins.
- Falta aba de perfil com edição de atributos e foto.
- Falta notificação.
- Falta sequência de treino.
- Dashboard ainda usa gráficos e percentuais mockados.
- Módulo de fala não funcional.
- Feedback de fala simulado com números irreais.
- Módulo escrever com feedback simulado e números irreais.
- Flashcards sem adicionar/excluir palavras.
- Comunidade deve ficar para próxima versão com tela ofuscada.
- Progresso usa valores irreais.
- Simulado não permite selecionar opção e finalizar prova.
- Dados não persistem no banco real.

## Tarefa
Crie ou atualize:
`docs/10-audits/current-functional-gap-audit.md`

O relatório deve conter:
1. rotas existentes;
2. funcionalidades realmente funcionais;
3. funcionalidades apenas visuais/mockadas;
4. backend Spring Boot existente e endpoints atuais;
5. entidades/tabelas existentes;
6. pontos sem persistência real;
7. ordem recomendada de correção;
8. riscos de alterar tudo de uma vez.

## Regras
- Não alterar código de aplicação.
- Não criar backend novo.
- Não recriar front-end.
- Apenas auditar e documentar.
