<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Frontend Spec — Auth

## Rotas
- `/login`;
- `/register`.

## Contexto atual
As telas já existem de forma mockada. Não recriar visual sem necessidade. Adaptar comportamento, validações e integração.

## Componentes esperados
- LoginPage;
- RegisterPage;
- AuthForm;
- PasswordInput;
- AuthErrorMessage;
- ProtectedRoute;
- AuthProvider/AuthStore, conforme arquitetura.

## Client API
Criar ou adaptar `authApiClient` com:
- register(payload);
- login(payload);
- me();
- logout().

## Estado de UI
- idle;
- submitting;
- authenticated;
- unauthenticated;
- error.

## Regras
- não armazenar senha em estado persistente;
- exibir erros claros;
- redirecionar usuário autenticado para dashboard;
- proteger rotas privadas;
- não usar mock após integração real.

## Critério de pronto
- login/cadastro funcionais;
- rotas privadas bloqueadas sem autenticação;
- usuário autenticado acessível ao layout;
- mocks removidos ou isolados como fallback de desenvolvimento.
