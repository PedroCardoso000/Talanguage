# Feature Spec — Onboarding

## Objetivo

Coletar, após o cadastro, o contexto mínimo necessário para personalizar futuramente a jornada de prática do aluno sem misturar onboarding com credenciais ou autenticação.

## Escopo

- consultar o onboarding do usuário autenticado;
- criar ou atualizar o próprio onboarding;
- informar se o onboarding está concluído;
- permitir que `GET /api/auth/me` exponha apenas `onboardingCompleted`.

## Fora de escopo

- alterar o payload de cadastro;
- recomendações automáticas;
- IA ou geração de plano de estudos;
- dados de outro usuário;
- respostas abertas além de `primaryGoal` e da descrição exigida para ocupação `OTHER`.

## Critério de pronto

- dados válidos são persistidos por usuário;
- atualização não cria registro duplicado;
- regras de listas, valores controlados e descrição de `OTHER` são aplicadas no domínio;
- endpoints exigem autenticação;
- Auth permanece separado e expõe apenas o indicador de conclusão.
