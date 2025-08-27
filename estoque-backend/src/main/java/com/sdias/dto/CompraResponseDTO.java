package com.sdias.dto;

import java.time.LocalDateTime;

import com.sdias.model.Compra;

// DTO para representar a resposta de uma compra
public class CompraResponseDTO {
    private Long id;
    private String nomeFornecedor;
    private LocalDateTime dataCompra;
    // Adicione outros campos que queira mostrar

    public CompraResponseDTO(Compra compra) {
        this.id = compra.getId();
        this.nomeFornecedor = compra.getFornecedor().getNomeFantasia();
        this.dataCompra = compra.getDataCompra();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeFornecedor() { return nomeFornecedor; }
    public void setNomeFornecedor(String nomeFornecedor) { this.nomeFornecedor = nomeFornecedor; }
    public LocalDateTime getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDateTime dataCompra) { this.dataCompra = dataCompra; }
}
