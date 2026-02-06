# 📦 Sistema de Gerenciamento de Estoque e Vendas — SDias

Backend completo para gerenciamento de **estoque, compras e vendas** de uma empresa de produtos olfativos (essências, aromatizadores e embalagens).  
O projeto foi desenvolvido com foco em **boas práticas de arquitetura**, **segurança**, **controle de dados** e **escalabilidade**, utilizando o ecossistema Spring.

---

## 🧠 Visão Geral

O **SDias** é uma API RESTful que permite:

- Controle completo de produtos e matérias-primas
- Gestão de clientes, fornecedores e usuários
- Registro de compras (entrada) e vendas (saída)
- Controle automático de estoque
- Autenticação segura via JWT

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Security + JWT**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **REST Client (VS Code)**

---

## 🏗️ Arquitetura

O projeto segue o padrão de **arquitetura em camadas**, garantindo organização, manutenção e escalabilidade:

Controller → Service → Repository → Database


### Camadas

- **Controller**  
  Exposição dos endpoints REST e gerenciamento das requisições HTTP.

- **Service**  
  Lógica de negócio, regras e validações do sistema.

- **Repository**  
  Persistência de dados utilizando Spring Data JPA.

- **Entity / Model**  
  Mapeamento das tabelas do banco de dados.

- **DTO (Data Transfer Object)**  
  Transporte seguro de dados entre camadas, evitando exposição indevida de entidades.

---

## 🔐 Segurança e Autenticação

A API utiliza **JWT (JSON Web Token)** para autenticação stateless.

### Fluxo de Segurança

1. **Login**
   - O usuário informa suas credenciais.
   - O sistema retorna um token JWT assinado.

2. **Acesso aos Endpoints**
   - Todas as rotas protegidas exigem o header:
     ```
     Authorization: Bearer {token}
     ```

3. **Criptografia de Senhas**
   - As senhas são criptografadas com **BCrypt** antes de serem persistidas.
   - Nenhuma senha é armazenada em texto puro.

---

## 🗄️ Estrutura do Banco de Dados

Banco de dados: **`sdias`**

### Principais Domínios

- **Produtos**
  - produtos
  - essencias
  - tipos_produto
  - embalagens

- **Usuários e Atores**
  - usuarios
  - clientes
  - fornecedores

- **Operações**
  - pedidos (vendas)
  - compras (entrada de estoque)

- **Logística**
  - movimentacoes_estoque  
    > Responsável por registrar toda alteração de saldo

---

## ⚙️ Como Executar o Projeto

### 📌 Pré-requisitos

- Java 17 instalado
- MySQL Server em execução
- Maven (ou Maven Wrapper)

---

### 1️⃣ Configurar o Banco de Dados

Execute o script SQL:
```bash
/sql/setup.sql
```

Esse script criará o schema `sdias` e todas as tabelas necessárias.

---

### 2️⃣ Configurar Variáveis de Ambiente

O sistema possui um **DataInitializer** que cria automaticamente um usuário administrador caso as variáveis abaixo estejam configuradas:

```bash
export ADMIN_LOGIN="seu_usuario"
export ADMIN_SENHA="sua_senha_segura"

```
### 3️⃣ Executar a Aplicação
```bash
./mvnw spring-boot:run
```

## A API ficará disponível em:

```bash
http://localhost:8080
```

### 🧪 Testes da API

Na pasta /tests, existe um arquivo .http preparado para uso com a extensão
REST Client do VS Code.

Cobertura de Testes

Autenticação (Login e Token)

CRUD de:

- Essências

- Produtos

- Clientes

- Fluxo completo de venda:

- Criação do pedido

- Baixa automática de estoque

### 📌 Status do Projeto

- ✅ Funcional
- 🔧 Em constante evolução
- 📈 Estruturado para expansão futura (relatórios, dashboard, frontend)

### ✍️ Autor

Roberto Dias
🎓 Estudante de Sistemas de Informação — UVV
💻 Desenvolvedor Java | Analista de Dados
