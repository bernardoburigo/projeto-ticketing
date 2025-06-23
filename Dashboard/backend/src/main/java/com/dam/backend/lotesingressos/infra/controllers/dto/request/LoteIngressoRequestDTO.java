package com.dam.backend.lotesingressos.infra.controllers.dto.request;

import jakarta.annotation.Nullable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record LoteIngressoRequestDTO(
        Integer nome,
        Integer evento,
        Integer tipoIngresso,
        BigDecimal preco,
        Integer quantidadeTotal,
        @Nullable Integer quantidadeVendida,
        ZonedDateTime dataInicioVenda,
        ZonedDateTime dataFinalVenda) {
}
