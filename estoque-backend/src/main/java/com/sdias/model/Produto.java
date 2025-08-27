package com.sdias.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    private String sku; // Código único do produto

    @Column(length = 100, nullable = false)
    private String nome; // Nome do produto

    @Column(nullable = false)
    private Integer quantidade = 0; // Estoque inicial do produto

    // Muitos Produtos podem usar a mesma Essência
    @ManyToOne
    @JoinColumn(name = "essencia_id", nullable = false)
    private Essencia essencia;

    // Muitos Produtos podem usar a mesma Embalagem
    @ManyToOne
    @JoinColumn(name = "embalagem_id", nullable = false)
    private Embalagem embalagem;

    @Column(name = "preco_venda", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoVenda;

    public Produto() {}

    public Produto(String sku, String nome, Essencia essencia, Embalagem embalagem, BigDecimal precoVenda, Integer quantidade) {
        this.sku = sku;
        this.nome = nome;
        this.essencia = essencia;
        this.embalagem = embalagem;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    // --- Getters e Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Essencia getEssencia() {
        return essencia;
    }

    public void setEssencia(Essencia essencia) {
        this.essencia = essencia;
    }

    public Embalagem getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }
}
