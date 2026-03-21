# Algamoney API 💰

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen)
![Java](https://img.shields.io/badge/Java-21-blue)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20Layers-orange)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

## 📖 Sobre o Projeto
O **Algamoney API** é um sistema robusto de controle financeiro pessoal. Ele permite o gerenciamento completo de lançamentos (receitas e despesas), categorias e pessoas, oferecendo uma base sólida para aplicativos de gestão financeira.

---

## 🏗️ Arquitetura (C4 Model)

Utilizamos o **C4 Model** para descrever a arquitetura do sistema em diferentes níveis:

### 1. Diagrama de Contexto (Nível 1)
Mostra como a API se integra com usuários e sistemas externos.

```mermaid
C4Context
    title Diagrama de Contexto (Nível 1)
    Person(user, "Usuário", "Usuário final do sistema de controle financeiro.")
    System(algamoney, "Algamoney API", "Sistema de controle financeiro pessoal.")
    System_Ext(azure, "Azure Key Vault", "Serviço de gerenciamento de segredos e credenciais.")

    Rel(user, algamoney, "Gerencia finanças usando", "HTTPS/REST")
    Rel(algamoney, azure, "Busca segredos de configuração", "HTTPS")
```

### 2. Diagrama de Contêiner (Nível 2)
Detalha os componentes internos da solução Algamoney.

```mermaid
C4Container
    title Diagrama de Contêiner (Nível 2)
    Person(user, "Usuário", "Usuário final.")
    System_Boundary(c1, "Algamoney System") {
        Container(api, "API Application", "Java/Spring Boot", "Fornece endpoints REST para controle financeiro.")
        ContainerDb(db, "Database", "MySQL", "Armazena dados de usuários, lançamentos e categorias.")
    }
    System_Ext(azure, "Azure Key Vault", "Gerenciamento de segredos e senhas do DB.")

    Rel(user, api, "Faz requisições para", "HTTPS/JSON")
    Rel(api, db, "Lê e escreve dados", "JDBC/JPA")
    Rel(api, azure, "Busca credenciais", "HTTPS")
```

---

## 🧩 Design Patterns e SOLID

A API foi desenhada para ser modular e fácil de manter, aplicando os seguintes padrões:

### Fluxo de Criação (Observer Pattern)
Abaixo, o fluxo de criação de um novo recurso utilizando o padrão **Observer (Eventos do Spring)** para desacoplar a lógica de geração de Headers HTTP:

```mermaid
sequenceDiagram
    participant Client
    participant Resource
    participant Mapper
    participant Service
    participant DB
    participant Listener as EventListener (Observer)

    Client->>Resource: POST /recursos
    Resource->>Mapper: toEntity(dto)
    Mapper-->>Resource: entity
    Resource->>Service: criar(entity)
    Service->>DB: save()
    DB-->>Service: savedEntity
    Service-->>Resource: savedEntity
    Resource->>Listener: publishEvent(RecursoCriado)
    Listener-->>Resource: setHeader("Location")
    Resource-->>Client: 201 Created (Location Header)
```

### Padrões Aplicados
- **Mapper Pattern**: Desacoplamento entre Entidades de Banco de Dados e DTOs via `PessoaMapper` e `CategoriaMapper`.
- **Observer Pattern**: Utilizado para tratar eventos de infraestrutura (como Location Headers) de forma desacoplada.
- **Domain-Driven Update**: Lógica de atualização de dados encapsulada no modelo de domínio (`Pessoa.atualizarDados()`), removendo lógica anêmica do Service.

---

## 🛠️ Stack Tecnológica
- **Linguagem**: Java 21 (LTS)
- **Framework**: Spring Boot 3.3.2
- **Banco de Dados**: MySQL 8+
- **Migrações**: Flyway
- **Segurança**: Azure Key Vault integration
- **Documentação**: SpringDoc OpenAPI (Swagger)
- **Testes**: JUnit 5, Mockito

---

## 🚀 Como Executar

### Pré-requisitos
- JDK 21
- Maven 3.9+
- MySQL instalado e rodando

### Configuração
1. Configure as variáveis em seu ambiente ou no `application-dev.yml`:
   ```yaml
   NAMEDB: nome_do_banco
   USERNAMEDB: usuario_mysql
   ```
2. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Acesse o Swagger em: `http://localhost:8080/swagger-ui.html`

---

## 📄 Licença
Este projeto está sob a licença MIT. 

---
*Relatório de refatoração detalhado disponível em: [relatorio-refatoracao-algamoney.html](./relatorio-refatoracao-algamoney.html)*
