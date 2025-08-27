package com.sdias.model;
/*
CREATE TABLE IF NOT EXISTS itens_pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
 */

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "itens_pedido")
public class ItemPedido{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable= false)
    @JsonBackReference
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable= false)
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    public ItemPedido(){

    }

    public Long getId(){
        return id;
    }

    public Pedido getPedido(){
        return pedido;
    }

    public Produto getProduto(){
        return produto;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}