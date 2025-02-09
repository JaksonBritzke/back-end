# Projeto Back-end 

**Importante**: Esta aplicação depende do Backend desenvolvido em Quarkus estar em execução.

Este projeto foi desenvolvido utilizando Angular 19 e PrimeNG 19 como biblioteca de componentes UI.

## Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- Node.js (Recomendada a versão LTS mais recente)
- NPM (Node Package Manager)
- Angular CLI versão 19.1.6 ou superior

Para instalar o Angular CLI globalmente, execute:

```bash
npm install -g @angular/cli
```

## Instalação

1. Clone o repositório do projeto
2. Navegue até a pasta do projeto
3. Instale as dependências executando:

```bash
npm install
```

## Executando a aplicação

Para iniciar o servidor de desenvolvimento:

```bash
npm start
```

ou

```bash
ng serve
```

A aplicação estará disponível em `http://localhost:4200/`

## Scripts disponíveis

- `npm start` - Inicia o servidor de desenvolvimento
- `npm run build` - Compila o projeto para produção
- `npm run watch` - Compila o projeto em modo de observação
- `npm test` - Executa os testes unitários
- `npm run serve:ssr:Front-End` - Executa o servidor SSR (Server-Side Rendering)

## Bibliotecas principais

O projeto utiliza as seguintes bibliotecas principais:

- Angular 19.1.0
- PrimeNG 19.0.6
- PrimeFlex 3.3.1
- PrimeIcons 7.0.0

## Desenvolvimento

A aplicação está configurada com:

- TypeScript 5.7.2
- RxJS 7.8.0
- Suporte a SSR (Server-Side Rendering)
- Testes com Jasmine e Karma

## Build

Para gerar uma build de produção:

```bash
npm run build
```

Os arquivos serão gerados no diretório `dist/`.

## Servidor SSR

Para executar o servidor SSR após o build:

```bash
npm run serve:ssr:Front-End
```

## Testes

Para executar os testes unitários:

```bash
npm test
```

---
*Observação: Projeto desenvolvido para fins de estudo, utilizando configurações simplificadas(Cors x user\password database).*
*Observação: Certifique-se de manter todas as dependências atualizadas para garantir a melhor compatibilidade e segurança.*