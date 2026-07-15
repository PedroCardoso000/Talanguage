# Talanguage - Implementation Status

Use este arquivo para marcar o andamento das execucoes.

| Ordem | Prompt | Status | Observacoes |
|---|---|---|---|
| 001 | Criar UI primitives | Concluido | UI primitives criados em `src/components/ui`; `lint` e `build` executados. |
| 002 | Corrigir encoding | Concluido | Mojibake corrigido nos textos do front-end; `lint` e `build` executados. |
| 003 | Criar contracts e API clients base | Concluido | Fundacao criada em `src/api` e `src/features/*`; goals/mock-test seguem provisorios por falta de api-spec. |
| 004 | Centralizar mocks por feature | Concluido | Mocks de conteudo e copy foram movidos para `src/features/*/mocks`; store e helpers apontam para essas fontes. |
| 005 | Extrair componentes compartilhados | Concluido | Metricas, progress meters, feedback panels e numeric goal fields extraidos para `src/components/shared`. |
| 006 | Alinhar rotas e matriz de navegacao | Concluido | Matriz atualizada para refletir `/write`, `/review`, `/goals` e `/mock-test` sem alterar rotas. |
| 007 | Preparar Auth para integracao | Concluido | Auth isolado em `authApi`, `auth-actions` e mock adapter; login/cadastro fake seguem navegaveis sem regra de sessao nas paginas. |
| 008 | Criar fundacao backend | Concluido | Estrutura neutra criada em `apps/api` sem framework nem dependencias; stack oficial do backend segue pendente. |
| 009 | Implementar Auth backend | Concluido | Backend Auth em Spring Boot com use cases, endpoint, bcrypt, sessao opaca em memoria e testes de aplicacao/API. |
| 010 | Integrar Auth frontend-backend | Concluido | Frontend Auth passou a consumir o backend real via `authApi`; token e preferencias locais ficaram explicitos, e os guards validam sessao com `/api/auth/me`. |
| 011 | Preparar Progress/Dashboard read model | Concluido | Read model compartilhado extraido para `src/features/progress/lib/read-model.ts`; dashboard e progress passaram a consumir hooks de feature em vez de ler/calcular dados direto na pagina. |
| 012 | Preparar Goals | Concluido | Contracts separados em request/response; persistencia saiu da UI para `goalsApi` + mock adapter + hook de feature com loading/error/success. |
| 013 | Preparar Review/Flashcards | Concluido | Review passou a usar contracts separados, `reviewApi` com mock adapter de list/review e hook de sessao; a pagina deixou de chamar store e mock direto. |
| 014 | Preparar Mock Test | Concluido | Simulado ganhou contracts explicitos, `mockTestApi` com adapter mock e hook de sessao; scoring e recomendacao sairam da pagina e ficaram em fronteira propria. |
| 015 | Preparar Writing Review | Concluido | Writing review passou a usar contracts separados, `writingApi` com mock adapter e hook de feature; o feedback fake saiu da pagina e ficou isolado na fronteira da feature. |
| 016 | Preparar Speaking Practice | Concluido | Speaking practice passou a usar contracts separados, `speakingApi` com mock adapter de topicos/sessao e hook de feature; sessao, feedback e progresso sairam da pagina. |
| 017 | Hardening da primeira versao | Concluido | Dashboard/progress passaram a respeitar a fronteira `api -> mock adapter -> hook`; matriz de rotas foi atualizada e validacoes criticas executadas. |
