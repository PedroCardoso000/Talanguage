<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Backend Spec — Auth

## Casos de uso

### RegisterUserUseCase
Entrada:
- name;
- email;
- password.

Saída:
- user;
- accessToken.

Regras:
- validar e-mail;
- verificar unicidade;
- validar senha;
- gerar hash;
- criar usuário;
- emitir token/sessão.

### LoginUserUseCase
Entrada:
- email;
- password.

Saída:
- user;
- accessToken.

Regras:
- localizar usuário por e-mail;
- comparar senha com hash;
- rejeitar credenciais inválidas;
- emitir token/sessão.

### GetAuthenticatedUserUseCase
Entrada:
- userId.

Saída:
- usuário autenticado.

## Portas/interfaces
- UserRepository;
- PasswordHasher;
- TokenService ou SessionService;
- AuthContext.

## Infraestrutura
- implementação de hash de senha;
- implementação de token/sessão;
- persistência de usuário;
- middleware/guard de autenticação.

## Proibido
- regra de autenticação dentro do controller;
- senha em log;
- senha no response;
- autenticação fake em produção.
