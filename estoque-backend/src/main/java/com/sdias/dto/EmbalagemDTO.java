package com.sdias.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EmbalagemDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O custo é obrigatório")
    @Positive(message = "O custo deve ser maior que zero")
    private BigDecimal custo;

    @NotNull(message = "O tipoProdutoId é obrigatório")
    private Long tipoProdutoId;

    // Getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getCusto() { return custo; }
    public void setCusto(BigDecimal custo) { this.custo = custo; }

    public Long getTipoProdutoId() { return tipoProdutoId; }
    public void setTipoProdutoId(Long tipoProdutoId) { this.tipoProdutoId = tipoProdutoId; }
}
