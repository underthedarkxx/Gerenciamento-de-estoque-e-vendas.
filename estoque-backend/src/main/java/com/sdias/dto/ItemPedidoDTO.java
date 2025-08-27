package com.sdias.dto;

import java.math.BigDecimal;

import com.sdias.model.ItemPedido;

public class ItemPedidoDTO {
    private Long id;
    private Long produtoId;   // <- para criação
    private String produto;   // <- sku ou nome para leitura
    private Integer quantidade;
    private BigDecimal precoUnitario;

    public ItemPedidoDTO() {}

    public ItemPedidoDTO(ItemPedido item) {
        this.id = item.getId();
        this.produtoId = item.getProduto().getId();
        this.produto = item.getProduto().getSku(); // ou .getNome() se existir
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPrecoUnitario();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public String getProduto() { return produto; }
    public void setProduto(String produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
}
