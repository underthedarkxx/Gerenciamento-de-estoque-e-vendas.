package com.sdias.dto;

import java.math.BigDecimal;

public record ItemCompraDTO(
    Long produtoId,
    Integer quantidade,
    BigDecimal custoUnitario
) {}