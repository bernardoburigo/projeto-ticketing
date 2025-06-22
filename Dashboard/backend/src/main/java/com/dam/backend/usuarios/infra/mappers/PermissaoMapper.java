package com.dam.backend.usuarios.infra.mappers;

import com.dam.backend.entities.RolePermissaoEntity;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import com.dam.backend.usuarios.infra.controllers.dto.response.PermissaoResponseDTO;
import java.util.List;
import java.util.Set;

public final class PermissaoMapper {

    private PermissaoMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static List<PermissaoResponseDTO> toListDTO(Set<RolePermissaoEntity> permissoes) {
        return permissoes.stream()
                .map(permissao -> PermissaoResponseDTO.builder()
                        .id(permissao.getId())
                        .nome(permissao.getPermissao().getPermissao())
                        .build())
                .toList();
    }

}
