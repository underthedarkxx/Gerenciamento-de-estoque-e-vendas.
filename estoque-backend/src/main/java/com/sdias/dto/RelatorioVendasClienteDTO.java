package com.sdias.dto;

import java.math.BigDecimal;

public class RelatorioVendasClienteDTO {

    private Long clienteId;
    private String nomeCliente;
    private int totalPedidos;
    private BigDecimal valorTotalVendido;

    public RelatorioVendasClienteDTO(Long clienteId, String nomeCliente, int totalPedidos, BigDecimal valorTotalVendido) {
        this.clienteId = clienteId;
        this.nomeCliente = nomeCliente;
        this.totalPedidos = totalPedidos;
        this.valorTotalVendido = valorTotalVendido;
    }

    // Getters e Setters
    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public int getTotalPedidos() {
        return totalPedidos;
    }
    public void setTotalPedidos(int totalPedidos) {
        this.totalPedidos = totalPedidos;
    }
    public BigDecimal getValorTotalVendido() {
        return valorTotalVendido;
    }
    public void setValorTotalVendido(BigDecimal valorTotalVendido) {
        this.valorTotalVendido = valorTotalVendido;
    }
}