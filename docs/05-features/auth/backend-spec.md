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

### RequestPasswordResetUseCase
Entrada:
- email.

Saída:
- nenhuma informação sobre existência do usuário; resposta sempre genérica.

Regras:
- aplicar limitação simples de solicitações repetidas;
- invalidar tokens anteriores quando o usuário existir;
- gerar token aleatório com expiração de 15 minutos;
- persistir somente o hash;
- enviar o token exclusivamente por `PasswordResetNotifier`;
- não registrar e-mail, token ou resultado da busca.

### ResetPasswordUseCase
Entrada:
- token;
- newPassword.

Regras:
- rejeitar token inválido, expirado ou consumido com erro genérico;
- validar a nova senha com a política existente;
- substituir o hash da senha;
- consumir o token;
- invalidar sessões anteriores do usuário.

## Portas/interfaces
- UserRepository;
- PasswordHasher;
- TokenService ou SessionService;
- AuthContext.
- PasswordResetTokenRepository;
- ApplicationClock;
- PasswordResetTokenGenerator;
- PasswordResetNotifier;
- PasswordResetRequestLimiter.

## Infraestrutura
- implementação de hash de senha;
- implementação de token/sessão;
- persistência de usuário;
- middleware/guard de autenticação.
- persistência de tokens de recuperação;
- gerador criptograficamente seguro e hash SHA-256;
- adaptador de notificação. Não existe integração de e-mail autorizada no repositório; sua implementação externa permanece pendência bloqueante.

## Proibido
- regra de autenticação dentro do controller;
- senha em log;
- senha no response;
- autenticação fake em produção.
