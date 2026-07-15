<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Domain Spec — Community

## Entidades

### PracticeGroup
Campos:
- id;
- title;
- language;
- level;
- description;
- memberCount;
- active.

### PracticePartnerProfile
Campos:
- userId;
- displayName;
- languagesPracticed;
- level;
- availabilityNote;

### CommunityInterest
Representa intenção do usuário de participar/conectar.

Campos:
- id;
- userId;
- targetType;
- targetId;
- createdAt.

## Value Objects
- Language;
- ProficiencyLevel;
- AvailabilityNote.

## Regras
1. Usuário autenticado pode registrar interesse.
2. Interesse duplicado deve ser evitado.
3. Comunidade não deve armazenar conversas na primeira versão.
4. Dados públicos devem ser mínimos.

## Eventos
- PracticeGroupInterestRegistered;
- PracticePartnerInterestRegistered.
