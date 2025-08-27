package com.sdias.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sdias.model.enums.TipoMovimentacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "movimentacoes_estoque")
public class MovimentacaoEstoque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "data_movimentacao", nullable = false, updatable = false)
    private LocalDateTime dataMovimentacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipoMovimentacao;

    @NotNull
    @Positive
    private Integer quantidade;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    @JsonBackReference("compra-movimentacao")
    private Compra compra;

    // Construtor vazio
    public MovimentacaoEstoque() {}

    // Preencher a data automaticamente
    @PrePersist
    public void prePersist() {
        if (dataMovimentacao == null) {
            dataMovimentacao = LocalDateTime.now();
        }
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }

    public TipoMovimentacao getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }
}

