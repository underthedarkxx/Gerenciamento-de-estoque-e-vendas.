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
@Table(name = "embalagens")
public class Embalagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    // ALTERAÇÃO: Trocamos o campo 'String tipo' pelo relacionamento com a entidade 'TipoProduto'
    @ManyToOne
    @JoinColumn(name = "tipo_produto_id", nullable = false)
    private TipoProduto tipoProduto;

    @Column(precision = 10, scale = 2)
    private BigDecimal custo;

    // Construtor vazio
    public Embalagem() {
    }

    public Embalagem(String nome, TipoProduto tipoProduto, BigDecimal custo) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.custo = custo;
    }

    // Getters e Setters (agora com getTipoProduto e setTipoProduto)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }
}