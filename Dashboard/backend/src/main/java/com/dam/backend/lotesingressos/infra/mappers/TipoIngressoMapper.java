package com.dam.backend.lotesingressos.infra.mappers;

import com.dam.backend.entities.TipoIngressoEntity;
import com.dam.backend.lotesingressos.infra.controllers.dto.response.TipoIngressoResponseDTO;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;

public final class TipoIngressoMapper {

    private TipoIngressoMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static TipoIngressoResponseDTO toDTO(TipoIngressoEntity entity) {
        return TipoIngressoResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }
}
