package com.dam.backend.usuarios.infra.controllers.dto.response;

import com.dam.backend.entities.RolePermissaoEntity;
import java.util.Set;
import lombok.Builder;

@Builder
public record RoleResponseDTO(
        Integer id,
        String nome,
        Set<RolePermissaoEntity> rolePermissao) {
}
