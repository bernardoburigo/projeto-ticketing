package com.dam.backend.usuarios.infra.controllers.dto.response;

import com.dam.backend.entities.RoleEntity;
import lombok.Builder;

@Builder
public record PaginarUsuarioResponseDTO(
        Integer id,
        String nome,
        String email,
        RoleEntity role,
        String ativo,
        String criadoEm,
        String modificadoEm) {
}
