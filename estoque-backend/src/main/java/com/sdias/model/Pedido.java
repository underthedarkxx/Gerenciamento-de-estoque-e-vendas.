package com.sdias.model;
/*
CREATE TABLE IF NOT EXISTS pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    data_venda DATETIME NOT NULL,
    valor_total DECIMAL(10, 2),
    status VARCHAR(50),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);
*/
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sdias.model.enums.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference
    private Cliente cliente;

    @Column(name = "data_venda", nullable = false, updatable = false)
    private LocalDateTime dataPedido;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private StatusPedido status;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ItemPedido> itens;

    public Pedido(){

    }

    public Long getId(){
        return id;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public LocalDateTime getDataPedido(){
        return dataPedido;
    }

    public BigDecimal getValorTotal(){
        return valorTotal;
    }

    public StatusPedido getStatus(){
        return status;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }
    
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
    @PrePersist
    public void prePersist() {
        setDataPedido(LocalDateTime.now());
    }
}