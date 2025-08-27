package com.sdias.dto;

import java.util.List;

public class PedidoCreateDTO {
    private Long clienteId;
    private List<ItemPedidoDTO> itens;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}


