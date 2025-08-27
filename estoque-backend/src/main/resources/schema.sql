-- ===============================================
-- Banco de dados limpo para a empresa SDias
-- ===============================================
CREATE DATABASE IF NOT EXISTS sdias;
USE sdias;

-- ===============================================
-- 1. TABELAS DE SUPORTE E CATEGORIZAÇÃO
-- ===============================================
CREATE TABLE IF NOT EXISTS tipos_produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS essencias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao_olfativa VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS embalagens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo_produto_id BIGINT NOT NULL,
    custo DECIMAL(10,2),
    FOREIGN KEY (tipo_produto_id) REFERENCES tipos_produto(id)
);

-- ===============================================
-- 2. TABELAS DE ATORES
-- ===============================================
CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(50),
    data_cadastro DATETIME
);

CREATE TABLE IF NOT EXISTS fornecedores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_fantasia VARCHAR(150) NOT NULL,
    cnpj VARCHAR(20) UNIQUE,
    contato VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- ===============================================
-- 3. TABELAS CENTRAIS (PRODUTOS E MATÉRIAS-PRIMAS)
-- ===============================================
CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sku VARCHAR(50) UNIQUE,
    essencia_id BIGINT NOT NULL,
    embalagem_id BIGINT NOT NULL,
    preco_venda DECIMAL(10,2) NOT NULL,
    qtd_estoque INT DEFAULT 0,
    FOREIGN KEY (essencia_id) REFERENCES essencias(id),
    FOREIGN KEY (embalagem_id) REFERENCES embalagens(id)
);

CREATE TABLE IF NOT EXISTS materias_primas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    unidade_medida VARCHAR(20),
    qtd_estoque INT DEFAULT 0,
    fornecedor_id BIGINT,
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id)
);

-- ===============================================
-- 4. TABELAS DE TRANSAÇÕES
-- ===============================================
CREATE TABLE IF NOT EXISTS pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    data_venda DATETIME NOT NULL,
    valor_total DECIMAL(10,2),
    status VARCHAR(50),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS itens_pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

CREATE TABLE IF NOT EXISTS compras (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fornecedor_id BIGINT NOT NULL,
    data_compra DATETIME NOT NULL,
    valor_total DECIMAL(10,2),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id)
);

CREATE TABLE IF NOT EXISTS itens_compra (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    compra_id BIGINT NOT NULL,
    produto_id BIGINT,
    materia_prima_id BIGINT,
    quantidade INT NOT NULL,
    custo_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (compra_id) REFERENCES compras(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id),
    FOREIGN KEY (materia_prima_id) REFERENCES materias_primas(id)
);

-- ===============================================
-- 5. TABELA DE LOG (MOVIMENTAÇÃO DE ESTOQUE)
-- ===============================================
CREATE TABLE IF NOT EXISTS movimentacao_estoque (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id BIGINT,
    materia_prima_id BIGINT,
    data_movimentacao DATETIME NOT NULL,
    tipo_movimentacao VARCHAR(50) NOT NULL,
    quantidade INT NOT NULL,
    observacao VARCHAR(255),
    pedido_id BIGINT,
    compra_id BIGINT,
    FOREIGN KEY (produto_id) REFERENCES produtos(id),
    FOREIGN KEY (materia_prima_id) REFERENCES materias_primas(id),
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (compra_id) REFERENCES compras(id)
);

