package com.sdias.dto;

import java.util.List;

public record CompraDTO(
    Long fornecedorId,
    List<ItemCompraDTO> itens
) {}

