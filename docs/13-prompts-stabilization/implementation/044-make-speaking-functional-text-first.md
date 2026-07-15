# 044 — Make Speaking Functional Text-First

## Objetivo
Tornar o módulo Falar funcional em modo texto primeiro, sem áudio real, sem transcrição e sem IA em tempo real.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/05-features/speaking/feature-spec.md`
3. `docs/05-features/speaking/domain-spec.md`
4. `docs/05-features/speaking/api-spec.md`
5. `docs/05-features/speaking/backend-spec.md`
6. `docs/05-features/speaking/frontend-spec.md`
7. `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## Escopo
Implementar sessão de prática de fala por texto:
- escolher tema;
- iniciar sessão;
- usuário responde por texto;
- sistema devolve próxima pergunta simples;
- usuário finaliza;
- backend salva sessão e mensagens.

## Backend
Endpoints sugeridos:
- `GET /api/speaking/topics`
- `POST /api/speaking/sessions`
- `POST /api/speaking/sessions/{id}/messages`
- `POST /api/speaking/sessions/{id}/finish`

## Feedback permitido
Sem números irreais.
Usar resumo simples:
- total de mensagens;
- tempo aproximado;
- tema praticado;
- sugestão textual para próxima prática.

## Proibido
- Áudio real.
- WebRTC.
- Transcrição.
- Score aleatório.
- Gráfico inventado.
- Fingir fluência calculada sem critério.

## Critério de pronto
- Usuário inicia sessão.
- Usuário envia mensagens.
- Sistema responde com prompts simples.
- Sessão finaliza.
- Dados persistem.
- Atividade alimenta progresso.
