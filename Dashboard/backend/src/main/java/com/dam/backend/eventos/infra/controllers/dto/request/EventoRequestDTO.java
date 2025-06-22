package com.dam.backend.eventos.infra.controllers.dto.request;

import java.time.ZonedDateTime;

public record EventoRequestDTO(
        String nome,
        String descricao,
        ZonedDateTime dataInicio,
        ZonedDateTime dataFinal,
        Integer localEvento,
        Integer categoria,
        Integer organizador) {
}
