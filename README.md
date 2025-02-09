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
https://github.com/JaksonBritzke/back-end.git
2. Navegue até a pasta do projeto
3. Execute o comando:

```bash
./mvn clean install
```

4. Para iniciar a aplicação em modo de desenvolvimento:

```bash
 ./mvnw quarkus:dev 
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

## Testes

Para executar os testes:

```bash
./mvn test
```

## Health Check

Endpoint de verificação de saúde disponível em:
- `http://localhost:8080/q/health`

Para fins de visualização com dados, logo ao rodar a aplicação, executar os scripts abaixo no Banco de Dados criado anteriormente:

## Dados de Exemplo

### Scripts para Fornecedores

```sql
INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(307, '66778899000155', NULL, 'fastlog@gmail.com', 'Rodovia SP-101, Km 25', 'Fast Log Transportes', 'ATIVO', '11955667788');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(309, '33445566000133', NULL, 'mediclin@gmail.com', 'Rua das Clínicas, 75', 'MediClin Serviços Médicos', 'ATIVO', '11922113344');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(310, '77889900000122', NULL, 'fashionmoda@gmail.com', 'Rua da Moda, 12', 'Fashion Moda Ltda', 'ATIVO', '11933445566');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(311, '99887766000111', NULL, 'ecotech@gmail.com', 'Rua Verde, 90', 'EcoTech Sustentável', 'ATIVO', '11944556677');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(312, '55667788000100', NULL, 'maxauto@gmail.com', 'Av. Automóveis, 88', 'Max Auto Peças', 'ATIVO', '11955667799');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(313, '22334455000189', NULL, 'foodexpress@gmail.com', 'Rua dos Alimentos, 10', 'Food Express Delivery', 'ATIVO', '11966778800');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(314, '33446677000178', NULL, 'netvision@gmail.com', 'Rua Digital, 120', 'NetVision Telecom', 'ATIVO', '11977889911');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(315, '11224455000167', NULL, 'agroterra@gmail.com', 'Rodovia Rural, Km 10', 'AgroTerra Insumos', 'ATIVO', '11988990022');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(316, '99882211000156', NULL, 'conectbr@gmail.com', 'Rua Conectividade, 33', 'ConectBr Internet', 'ATIVO', '11999001133');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(317, '66557799000145', NULL, 'movelog@gmail.com', 'Av. da Logística, 55', 'MoveLog Transportes', 'ATIVO', '11910112244');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(308, '88990011000144', '2025-02-05', 'construsol@gmail.com', 'Rua dos Engenheiros, 200', 'Construsol Engenharia', 'BAIXADO', '11911223344');

INSERT INTO public.fornecedor
(codigo, cnpj, databaixa, email, endereco, razaosocial, situacao, telefone)
VALUES(305, '11223344000177', '2025-02-09', 'comercialbrasil@gmail.com', 'Rua dos Engenheiros, 278', 'Comercial Brasil Ltda', 'BAIXADO', '46988058054');
```

### Scripts para Produtos

```sql
INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(301, 'Garrafa Pet', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(302, 'Caixa de Papelão', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(303, 'Lata de Alumínio', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(304, 'Vidro Transparente', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(305, 'Saco Plástico', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(306, 'Papel Sulfite A4', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(307, 'Embalagem Tetra Pak', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(308, 'Bobina Plástica', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(309, 'Rolo de Papel Kraft', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(310, 'Tampa Plástica', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(311, 'Fita Adesiva Transparente', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(313, 'Frasco de Spray', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(314, 'Caixa de Isopor', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(315, 'Pote de Vidro com Tampa', 'ATIVO');

INSERT INTO public.produto
(codigo, descricao, situacao)
VALUES(312, 'Etiqueta Autocolante', 'INATIVO');
```


---
*Observação: Projeto desenvolvido para fins de estudo, utilizando configurações simplificadas(Cors x user\password database).*
*Observação: Certifique-se de que o PostgreSQL esteja em execução antes de iniciar a aplicação.*