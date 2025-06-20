package com.dam.backend.usuarios.infra.controllers.dto.response;

import com.dam.backend.entities.RolePermissaoEntity;

public record RoleResponseDTO(Integer id, String nome, RolePermissaoEntity rolePermissao) {
}
