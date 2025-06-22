package com.dam.backend.usuarios.infra.mappers;

import com.dam.backend.entities.RoleEntity;
import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import com.dam.backend.shared.utils.FormateDateUtil;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioCadastradoResponseDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioResponseDTO;

public final class UsuarioMapper {

    private UsuarioMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static UsuarioCadastradoResponseDTO toDTO(UsuarioEntity usuario) {
        return new UsuarioCadastradoResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole().getNome().trim().toLowerCase()
        );
    }

    public static UsuarioResponseDTO toEventoDTO(UsuarioEntity usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }

    public static UsuarioResponseDTO toPaginarOrBuscar(UsuarioEntity usuario) {
        RoleEntity role = usuario.getRole() != null ? usuario.getRole() : null;

        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(RoleMapper.toRoleDTO(role))
                .ativo(usuario.isAtivo())
                .criadoEm(FormateDateUtil.formatarDataZonedDateTime(usuario.getAudCriadoData()))
                .modificadoEm(FormateDateUtil.formatarDataZonedDateTime(usuario.getAudModificadoData()))
                .build();
    }
}
