package com.sdias.dto;

import java.math.BigDecimal;

import com.sdias.model.Produto;

public class ProdutoDTO {

    private Long id;
    private String sku;
    private String nome;
    private BigDecimal precoVenda;
    private Long essenciaId;
    private Long embalagemId;

    public ProdutoDTO() {}

    // Construtor que recebe a entidade Produto
    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.sku = produto.getSku();
        this.nome = produto.getNome(); // <-- LINHA CORRIGIDA
        this.precoVenda = produto.getPrecoVenda();
        this.essenciaId = produto.getEssencia() != null ? produto.getEssencia().getId() : null;
        this.embalagemId = produto.getEmbalagem() != null ? produto.getEmbalagem().getId() : null;
    }

    // --- GETTERS E SETTERS ---
    // (O resto do seu código está correto)

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

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Long getEssenciaId() {
        return essenciaId;
    }

    public void setEssenciaId(Long essenciaId) {
        this.essenciaId = essenciaId;
    }

    public Long getEmbalagemId() {
        return embalagemId;
    }

    public void setEmbalagemId(Long embalagemId) {
        this.embalagemId = embalagemId;
    }
}
