# Backend Spec — Onboarding

## GetMyOnboardingUseCase

Entrada:
- `authenticatedUserId`.

Saída:
- `completed: false` quando não houver registro;
- dados completos do próprio onboarding quando houver registro.

## SaveMyOnboardingUseCase

Entrada:
- `authenticatedUserId`;
- dados controlados do onboarding.

Regras:
- criar quando inexistente;
- atualizar o registro do mesmo usuário quando existente;
- validar invariantes no domínio;
- definir horários pelo servidor;
- não consultar nem aceitar outro `userId`.

## Porta

`LearnerOnboardingRepository`:
- `findByUserId(UserId)`;
- `save(LearnerOnboarding)`;
- `existsByUserId(UserId)` para o indicador em Auth.

## Persistência

- uma linha por usuário em `learner_onboarding`;
- `user_id` como chave primária e FK para `app_user`;
- listas persistidas em JSON textual seguindo o padrão já existente no projeto;
- entidade JPA separada da entidade de domínio.
