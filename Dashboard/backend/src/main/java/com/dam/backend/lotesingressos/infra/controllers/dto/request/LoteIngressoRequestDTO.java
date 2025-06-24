package com.dam.backend.lotesingressos.infra.controllers.dto.request;

import jakarta.annotation.Nullable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public record LoteIngressoRequestDTO(
        Integer evento,
        Integer tipoIngresso,
        BigDecimal precoInicial,
        @Nullable BigDecimal percentualAumento,
        Integer quantidadeLotes,
        List<Integer> quantidadesPorLote,
        ZonedDateTime dataInicioVenda,
        ZonedDateTime dataFinalVenda
) {}