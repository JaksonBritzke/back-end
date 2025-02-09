# Projeto Backend Quarkus

Este projeto foi desenvolvido utilizando Quarkus 3.18.2, PostgreSQL 16.6 e Java 17.

## Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- Java 17 (OpenJDK)
- Apache Maven 3.9.1
- PostgreSQL 16.6
- IDE de sua preferência (recomendado VSCode ou IntelliJ IDEA)

## Configuração do Banco de Dados

1. Instale o PostgreSQL 16.6
2. Crie um banco de dados chamado `bd_teste`
3. Configure o usuário e senha como:
   - Usuário: postgres
   - Senha: postgres

Ou altere as configurações no arquivo `application.properties` de acordo com sua instalação.

## Instalação e Execução

1. Clone o repositório do projeto
2. Navegue até a pasta do projeto
3. Execute o comando:

```bash
mvn clean install
```

4. Para iniciar a aplicação em modo de desenvolvimento:

```bash
mvn quarkus:dev
```

A aplicação estará disponível em `http://localhost:8080`

## Endpoints e Documentação

A documentação Swagger UI está disponível em:
- `http://localhost:8080/swagger-ui`

## Principais Dependências

O projeto utiliza as seguintes dependências principais:

- Quarkus 3.18.2
- Hibernate ORM com Panache
- RESTEasy
- PostgreSQL JDBC Driver
- Swagger UI
- Lombok 1.18.30

## Configurações CORS

O backend está configurado para aceitar requisições do frontend Angular que roda em `http://localhost:4200`

## Health Check

Endpoint de verificação de saúde disponível em:
- `http://localhost:8080/q/health`

## Desenvolvimento

- O projeto usa Java 17
- Hibernate ORM com modo de geração de banco de dados em `update`
- Swagger UI sempre incluído
- CORS configurado para desenvolvimento local

## Build

Para gerar um build de produção:

```bash
mvn package
```

Para gerar um build nativo:

```bash
mvn package -Pnative
```

## Testes

Para executar os testes:

```bash
mvn test
```

---
*Observação: Projeto desenvolvido para fins de estudo, utilizando configurações simplificadas(Cors x user\password database).*
*Observação: Certifique-se de que o PostgreSQL esteja em execução antes de iniciar a aplicação.*