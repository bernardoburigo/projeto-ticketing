package com.dam.backend.usuarios.infra.controllers.dto.response;

import lombok.Builder;

@Builder
public record PermissaoResponseDTO(Integer id, String nome) {
}
