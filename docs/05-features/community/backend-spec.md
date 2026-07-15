<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Backend Spec — Community

## Casos de uso
- ListPracticeGroupsUseCase;
- ListPracticePartnersUseCase;
- RegisterCommunityInterestUseCase.

## Portas/interfaces
- PracticeGroupRepository;
- PracticePartnerQueryService;
- CommunityInterestRepository.

## Regras
- dados podem iniciar seedados no banco;
- não implementar chat;
- não expor dados sensíveis;
- interesse duplicado deve retornar resposta idempotente ou erro controlado.
