# Current Production Readiness

## 1. Estrutura Atual do Projeto

- Frontend em Next.js App Router na raiz do workspace, com `src/app`, `src/components`, `src/features`, `src/store`, `src/lib` e `src/types`.
- Backend em `apps/api`, usando Java 17 + Spring Boot 3.3.2.
- Infra minima de containerizacao ja existe com `Dockerfile`, `docker-compose.yml` e `apps/api/Dockerfile`.
- Documentacao de implementacao 001-017 concluida em `docs/11-prompts/implementation-status.md`.
- Prompt de producao 018 e o primeiro da trilha `docs/12-prompts-production`.

## 2. Status do Frontend

- Rotas principais existem e buildam: `/login`, `/register`, `/dashboard`, `/speak`, `/write`, `/review`, `/goals`, `/progress`, `/mock-test`.
- UI esta navegavel e a composicao geral esta coerente para demo.
- Pags principais ja foram reduzidas para composicao + hooks de feature na maior parte dos fluxos.
- Auth do frontend ja consome backend real em `/api/auth`.
- Dashboard e progress agora respeitam a fronteira `api -> mock adapter -> hook`, mas ainda nao consomem backend real.
- Goals, review, mock test, writing e speaking usam API clients + mock adapters com `localStorage` como persistencia temporaria.

## 3. Status de UI Primitives

- Conjunto de primitives presente em `src/components/ui`.
- Itens encontrados:
  - `badge.tsx`
  - `button.tsx`
  - `card.tsx`
  - `input.tsx`
  - `progress-bar.tsx`
  - `select.tsx`
  - `textarea.tsx`
- Camada compartilhada tambem existe em `src/components/shared` para cards metricos, feedback, goal fields e progress meter.
- Estado atual: suficiente para MVP e demonstracao. Nao ha indicio de necessidade imediata de nova primitive para backend real.

## 4. Status de Contracts / API Clients

- Features com API client explicito:
  - `auth`
  - `dashboard`
  - `goals`
  - `mock-test`
  - `progress`
  - `review`
  - `speaking`
  - `writing`
- Features com pasta `contracts` dedicada:
  - `auth`
  - `dashboard`
  - `goals`
  - `mock-test`
  - `progress`
  - `review`
  - `speaking`
  - `writing`
- Estado atual:
  - `auth` ja fala com backend real.
  - `dashboard` e `progress` possuem clients e contracts, mas ainda usam mock adapters.
  - `goals`, `review`, `mock-test`, `writing` e `speaking` estao arquiteturalmente preparados, mas seguem mockados.
- Observacao importante:
  - `src/api/http-client.ts` ainda contem `notImplementedClient`, mas os clients ativos principais ja nao dependem dele para os fluxos preparados.

## 5. Status de Mocks

- Mocks foram empurrados para `src/features/*/mocks`, o que esta alinhado com a estrategia de isolamento.
- Ainda existe camada de compatibilidade em `src/data/mock-content.ts`.
- Persistencia local ainda e usada em:
  - goals
  - review
  - writing
  - speaking
  - auth mock legado
- Mocks restantes sao explicitos, nao escondidos nas paginas como antes.
- Ainda assim, o produto nao esta “desmockado”; ele esta apenas melhor compartmentalizado.

## 6. Status de Build / Lint / Test

- `npm run lint`: passou em 2026-07-08.
- `npm run build`: passou em 2026-07-08.
- `npm test`: nao existe no frontend raiz. O script esta ausente em `package.json`.
- Backend:
  - `mvn test` em `apps/api`: ja havia passado durante a fase anterior e segue sendo a referencia atual para a API de auth.
- Docker:
  - `docker compose config` ja havia validado a configuracao.
  - `docker compose build` nao pode ser concluido quando o daemon local do Docker nao estava ativo.

## 7. Lacunas Para Backend Real

- Dashboard ainda nao tem backend real para `/api/dashboard`.
- Progress ainda nao tem backend real para `/api/progress`.
- Goals nao possuem backend real.
- Flashcards/review nao possuem backend real.
- Writing nao possui backend real.
- Speaking nao possui backend real.
- Community nao esta implementada na aplicacao atual.
- Read models de dashboard/progress ainda dependem de dados derivados do store local.
- Zustand ainda concentra metricas e progresso que, em producao, deveriam ser read/write models vindos do backend.

## 8. Riscos Para Integracao

- O maior risco nao e UI. E verdade de dados.
- O store local ainda influencia read models e progresso agregado. Isso pode colidir com a futura autoridade do backend.
- `src/data/mock-content.ts` ainda existe como compatibilidade. Se nao for removido no momento certo, vira segunda fonte de verdade.
- O frontend nao possui suite de testes automatizados criticos, entao regressao de integracao sera facil de introduzir.
- O backend atual cobre apenas auth. O resto do app ainda depende de mocks com comportamento local.
- Nao ha evidencias de observabilidade minima integrada entre frontend e backend real.
- O repo nao esta em um git worktree inicializado nesta pasta, entao rastreabilidade local de diff/commit nao esta disponivel aqui.

## 9. Ordem Recomendada dos Proximos Prompts

1. `019-freeze-backend-stack`
   - Necessario para parar de improvisar base tecnica.
2. `020-fix-critical-quality`
   - Antes de ampliar backend real, convem eliminar inconsistencias de qualidade ainda visiveis.
3. `021-create-real-backend-foundation`
   - Backend precisa de fundacao alem de auth.
4. `022-implement-auth-backend`
   - Deve validar se a trilha de producao quer consolidar ou endurecer a auth ja existente.
5. `023-integrate-auth-frontend-backend`
   - Mesmo com auth ja funcional, este prompt provavelmente vai consolidar criterios de producao.
6. `024-implement-progress-backend`
   - Prioridade alta porque dashboard depende disso.
7. `025-integrate-dashboard-progress`
   - Sem esse passo, a app continua vendendo progresso local como se fosse dado real.
8. `026-implement-goals-real`
9. `027-implement-flashcards-real`
10. `028-implement-writing-functional`
11. `029-implement-speaking-functional-text`
12. `030-implement-community-simple`
13. `031-remove-dead-mocks`
14. `032-add-critical-tests`
15. `033-first-version-hardening`
16. `034-release-readiness-check`

## 10. Bloqueios Encontrados

- `npm test` nao existe no frontend. Isso bloqueia validacao automatizada minima no cliente ate o prompt de testes.
- Docker daemon local nao estava disponivel na verificacao anterior, o que impediu validar `docker compose build` ponta a ponta.
- A trilha de prompts de producao aparenta repetir auth/backend foundation, apesar de parte disso ja existir da fase 001-017. Isso nao bloqueia, mas exige leitura rigorosa para evitar retrabalho cego.
- A feature community aparece na trilha de producao, mas nao existe na app atual e nao deve ser assumida como pronta.

## Diagnostico Resumido

O projeto saiu do estado de mock acoplado para um estado intermediario mais serio: fronteiras existem, contracts existem, hooks existem, e auth/backend real ja comecou. O problema agora e simples de dizer e chato de executar: o frontend esta pronto para ser desmockado por feature, mas ainda nao esta sustentado por backend real fora de auth. Se a proxima fase tentar “entregar producao” sem enfrentar isso, vai apenas trocar nome de pasta por ilusao de maturidade.
