package com.sdias.dto;

import java.time.LocalDateTime;

import com.sdias.model.MovimentacaoEstoque;
import com.sdias.model.enums.TipoMovimentacao;

public class MovimentacaoEstoqueResponseDTO {
    private Long id;
    private String nomeProduto;
    private TipoMovimentacao tipo;
    private Integer quantidade;
    private LocalDateTime dataMovimentacao;
    private Long compraId;
    private Long pedidoId;

    public MovimentacaoEstoqueResponseDTO(MovimentacaoEstoque mov) {
        this.id = mov.getId();
        this.nomeProduto = mov.getProduto().getNome();
        this.tipo = mov.getTipoMovimentacao();
        this.quantidade = mov.getQuantidade();
        this.dataMovimentacao = mov.getDataMovimentacao();
        this.compraId = (mov.getCompra() != null) ? mov.getCompra().getId() : null;
        this.pedidoId = (mov.getPedido() != null) ? mov.getPedido().getId() : null;
    }

    // Getters
    public Long getId() { return id; }
    public String getNomeProduto() { return nomeProduto; }
    public TipoMovimentacao getTipo() { return tipo; }
    public Integer getQuantidade() { return quantidade; }
    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public Long getCompraId() { return compraId; }
    public Long getPedidoId() { return pedidoId; }
}
