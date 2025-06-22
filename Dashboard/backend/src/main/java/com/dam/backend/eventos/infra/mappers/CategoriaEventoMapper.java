package com.dam.backend.eventos.infra.mappers;

import com.dam.backend.entities.CategoriaEventoEntity;
import com.dam.backend.eventos.infra.controllers.dto.response.CategoriaEventoResponseDTO;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;

public final class CategoriaEventoMapper {

    private CategoriaEventoMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static CategoriaEventoResponseDTO toDTO(CategoriaEventoEntity categoria) {
        return CategoriaEventoResponseDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .build();
    }
}
