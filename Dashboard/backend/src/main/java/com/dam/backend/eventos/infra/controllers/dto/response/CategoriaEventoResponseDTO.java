package com.dam.backend.eventos.infra.controllers.dto.response;

import lombok.Builder;

@Builder
public record CategoriaEventoResponseDTO(Integer id, String nome) {
}
