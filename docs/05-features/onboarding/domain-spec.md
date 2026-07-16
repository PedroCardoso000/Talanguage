# Domain Spec — Onboarding

## Entidade LearnerOnboarding

Campos:
- `userId`, identidade e proprietário;
- `ageRange`, opcional;
- `occupation`;
- `occupationDescription`, somente para `OTHER`;
- `learningMotivations`, lista não vazia e sem duplicados;
- `primaryGoal`, texto obrigatório com até 240 caracteres;
- `difficultySkills`, lista não vazia e sem duplicados usando `SkillType`;
- `currentLevel`;
- `weeklyAvailabilityMinutes`, entre 1 e 10.080;
- `completedAt`, definido pelo servidor;
- `updatedAt`, definido pelo servidor.

## Valores controlados

- `AgeRange`: `UNDER_18`, `18_24`, `25_34`, `35_44`, `45_54`, `55_PLUS`.
- `Occupation`: `STUDENT`, `EMPLOYED`, `SELF_EMPLOYED`, `UNEMPLOYED`, `RETIRED`, `OTHER`.
- `LearningMotivation`: `CAREER`, `TRAVEL`, `STUDY`, `IMMIGRATION`, `CULTURE`, `PERSONAL_DEVELOPMENT`, `OTHER`.
- `CurrentLevel`: `BEGINNER`, `INTERMEDIATE`, `ADVANCED`.
- `difficultySkills`: valores do enum existente `SkillType`.

## Invariantes

1. O `userId` vem exclusivamente do contexto autenticado.
2. `learningMotivations` e `difficultySkills` não podem ser vazias nem conter duplicados.
3. `occupationDescription` é obrigatória apenas quando `occupation = OTHER` e deve ter no máximo 120 caracteres.
4. Para outras ocupações, `occupationDescription` não é persistida.
5. `primaryGoal` deve ter entre 1 e 240 caracteres.
6. `completedAt` é definido na primeira conclusão e preservado em atualizações.
7. Atualizações substituem os dados do mesmo usuário, sem criar outro onboarding.
