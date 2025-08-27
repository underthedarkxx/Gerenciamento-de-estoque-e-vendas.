package com.sdias.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.sdias.model.Pedido;
import com.sdias.model.enums.StatusPedido;

public class PedidoDTO {
    private Long id;
    private String cliente;
    private LocalDateTime dataPedido;
    private BigDecimal valorTotal;
    private StatusPedido status; // <- Alterado para enum
    private List<ItemPedidoDTO> itens;

    public PedidoDTO() {}

    // Construtor para retorno de Pedido
    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.cliente = pedido.getCliente().getNome();
        this.dataPedido = pedido.getDataPedido();
        this.valorTotal = pedido.getValorTotal();
        this.status = pedido.getStatus(); // enum direto
        this.itens = pedido.getItens() != null
                ? pedido.getItens().stream()
                        .map(ItemPedidoDTO::new)
                        .collect(Collectors.toList())
                : null;
    }

    // --- GETTERS E SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}



