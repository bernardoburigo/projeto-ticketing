package com.dam.backend.lotesingressos.infra.controllers.dto.response;

import lombok.Builder;

@Builder
public record TipoIngressoResponseDTO(Integer id, String nome) {
}
