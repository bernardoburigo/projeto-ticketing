package com.dam.backend.usuarios.infra.mappers;

import com.dam.backend.entities.UsuarioEntity;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
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
}
