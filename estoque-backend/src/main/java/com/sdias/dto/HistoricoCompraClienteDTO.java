package com.sdias.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistoricoCompraClienteDTO {

    private String nomeEssencia;
    private String nomeProduto;
    private int quantidadeComprada;
    private BigDecimal precoUnitarioPago;
    private LocalDateTime dataCompra;

    // Construtor vazio (opcional, mas boa prática)
    public HistoricoCompraClienteDTO() {
    }

    // ESTE CONSTRUTOR É OBRIGATÓRIO PARA A SUA @QUERY FUNCIONAR
    // A ordem e os tipos dos parâmetros devem bater EXATAMENTE com o seu SELECT new...
    public HistoricoCompraClienteDTO(String nomeEssencia, String nomeProduto, int quantidade, BigDecimal precoUnitario, LocalDateTime dataCompra) {
        this.nomeEssencia = nomeEssencia;
        this.nomeProduto = nomeProduto;
        this.quantidadeComprada = quantidade;
        this.precoUnitarioPago = precoUnitario;
        this.dataCompra = dataCompra;
    }

    public String getNomeEssencia() {
        return nomeEssencia;
    }

    public void setNomeEssencia(String nomeEssencia) {
        this.nomeEssencia = nomeEssencia;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public BigDecimal getPrecoUnitarioPago() {
        return precoUnitarioPago;
    }

    public void setPrecoUnitarioPago(BigDecimal precoUnitarioPago) {
        this.precoUnitarioPago = precoUnitarioPago;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }
}
