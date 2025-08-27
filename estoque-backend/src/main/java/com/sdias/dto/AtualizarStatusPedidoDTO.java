package com.sdias.dto;

import com.sdias.model.enums.StatusPedido;

public class AtualizarStatusPedidoDTO {
    
    private StatusPedido status;

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
