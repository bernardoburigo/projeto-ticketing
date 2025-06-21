package com.dam.backend.usuarios.infra.controllers.dto.response;

import lombok.Builder;

@Builder
public record UsuarioResponseDTO(
        Integer id,
        String nome,
        String email,
        RoleResponseDTO role,
        boolean ativo,
        String criadoEm,
        String modificadoEm) {
}
