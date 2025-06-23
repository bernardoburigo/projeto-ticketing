package com.dam.backend.usuarios.infra.controllers.dto.response;

import lombok.Builder;

@Builder
public record OrganizadorResponseDTO(
        Integer id,
        String nome,
        String email,
        boolean ativo) {
}
