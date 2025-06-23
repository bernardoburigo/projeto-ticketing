package com.dam.backend.eventos.infra.controllers.dto.response;

import lombok.Builder;

@Builder
public record LoteIngressoEventoResponseDTO(Integer id, String nome) {
}
