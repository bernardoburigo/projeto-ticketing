package com.dam.backend.usuarios.infra.mappers;

import com.dam.backend.entities.RoleEntity;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import com.dam.backend.usuarios.infra.controllers.dto.response.RoleResponseDTO;

public final class RoleMapper {

    private RoleMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static RoleResponseDTO toRoleDTO(RoleEntity role) {
        return RoleResponseDTO.builder()
                .id(role.getId())
                .nome(role.getNome())
                .rolePermissao(PermissaoMapper.toListDTO(role.getPermissoes()))
                .build();
    }
}
