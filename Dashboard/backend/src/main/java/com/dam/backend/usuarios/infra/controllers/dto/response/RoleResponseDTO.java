package com.dam.backend.usuarios.infra.controllers.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record RoleResponseDTO(
        Integer id,
        String nome,
        List<PermissaoResponseDTO> rolePermissao) {
}
