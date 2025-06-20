package com.dam.backend.usuarios.infra.mappers;

import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import com.dam.backend.shared.utils.FormateDateUtil;
import com.dam.backend.shared.utils.Util;
import com.dam.backend.usuarios.infra.controllers.dto.response.PaginarUsuarioResponseDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.UsuarioCadastradoResponseDTO;

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

    public static PaginarUsuarioResponseDTO toPaginarOrDetalhar(UsuarioEntity usuario) {
        return PaginarUsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .ativo(Util.verifyStatusAtivo(usuario.isAtivo()))
                .criadoEm(FormateDateUtil.formatarDataZonedDateTime(usuario.getAudCriadoData()))
                .modificadoEm(FormateDateUtil.formatarDataZonedDateTime(usuario.getAudModificadoData()))
                .build();
    }
}
