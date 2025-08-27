package com.sdias.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @Column(name = "data_compra", nullable = false)
    private LocalDateTime dataCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    @JsonManagedReference("compra-item") // Evita loops infinitos no JSON
    private List<ItemCompra> itens;

    @OneToMany(mappedBy = "compra")
    private List<MovimentacaoEstoque> movimentacoes;
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }

    public LocalDateTime getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDateTime dataCompra) { this.dataCompra = dataCompra; }

    public List<ItemCompra> getItens() { return itens; }
    public void setItens(List<ItemCompra> itens) { this.itens = itens; } // <-- Método necessário

    public List<MovimentacaoEstoque> getMovimentacoes() { return movimentacoes; }
    public void setMovimentacoes(List<MovimentacaoEstoque> movimentacoes) { this.movimentacoes = movimentacoes; }
}