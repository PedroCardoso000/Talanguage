# Test Spec — Onboarding

## Testes unitários

- cria onboarding válido e marca como concluído;
- preserva `completedAt` na atualização;
- rejeita listas vazias;
- rejeita listas duplicadas;
- exige descrição quando ocupação é `OTHER`;
- remove descrição para ocupação diferente de `OTHER`;
- rejeita objetivo vazio ou acima de 240 caracteres;
- rejeita disponibilidade fora de 1 a 10.080 minutos.

## Testes de API e integração

- GET e PUT retornam 401 sem autenticação;
- GET retorna `completed: false` sem registro;
- PUT cria e retorna onboarding concluído;
- segundo PUT atualiza sem duplicar registro;
- enum inválido retorna 400;
- dados de usuários diferentes permanecem isolados;
- `GET /api/auth/me` retorna `onboardingCompleted` falso e depois verdadeiro.
