# Agenda de Contatos

Este projeto é uma aplicação CRUD (Create, Read, Update, Delete) para gerenciar contatos, desenvolvida em Java e utilizando o banco de dados MySQL.

## Descrição do Projeto

A Agenda de Contatos permite que os usuários adicionem, visualizem, atualizem e removam contatos. O sistema foi desenvolvido com o objetivo de praticar conceitos de programação em Java, além da interação com bancos de dados usando JDBC.

## Tecnologias Utilizadas

- Java
- MySQL
- JDBC

## Funcionalidades

- Adicionar novos contatos
- Listar todos os contatos
- Atualizar informações de contatos existentes
- Remover contatos

## Instalação

Para executar este projeto em sua máquina local, siga os passos abaixo:

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/gustavocnunes/CRUD.git

## Instruções Adicionais

### Instale o Mysql e crie um banco de dados: 

Executes os seguintes comandos: 

```sql
create table contatos(

id int not null auto_increment primary key,
nome varchar(40),
idade int,
dataCadastro date) 

