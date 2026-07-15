<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Domain Spec — Auth

## Entidades

### User
Representa uma pessoa cadastrada na plataforma.

Campos conceituais:
- id;
- name;
- email;
- passwordHash;
- preferredLanguages;
- nativeLanguage;
- createdAt;
- updatedAt.

## Value Objects

### Email
Regras:
- deve possuir formato válido;
- deve ser normalizado para comparação;
- deve ser único no sistema.

### Password
Regras:
- não deve ser armazenada em texto puro;
- deve atender à política mínima definida em segurança;
- validação de força deve ocorrer antes da criação do usuário.

### UserId
Identificador único do usuário.

## Regras de domínio
1. Um usuário deve ter e-mail único.
2. Um usuário deve ter nome identificável.
3. Senha nunca deve ser exposta fora da camada de autenticação.
4. Usuário autenticado é pré-condição para acessar módulos privados.

## Eventos de domínio
- UserRegistered;
- UserLoggedIn;
- UserLoggedOut.

## Observação
Autenticação é suporte transversal. Não deve conter regras de aprendizagem de idioma.
