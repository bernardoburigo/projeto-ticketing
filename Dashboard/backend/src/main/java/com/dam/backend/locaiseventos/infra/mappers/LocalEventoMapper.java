package com.dam.backend.locaiseventos.infra.mappers;

import com.dam.backend.entities.LocalEventoEntity;
import com.dam.backend.locaiseventos.infra.controllers.dto.response.LocaisEventoResponseDTO;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;

public final class LocalEventoMapper {

    private LocalEventoMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static LocaisEventoResponseDTO toDTO(LocalEventoEntity localEvento) {
        return LocaisEventoResponseDTO.builder()
                .id(localEvento.getId())
                .nome(localEvento.getNome())
                .cidade(localEvento.getCidade())
                .endereco(localEvento.getEndereco())
                .estado(localEvento.getEstado())
                .cep(localEvento.getCep())
                .capacidate(localEvento.getCapacidade())
                .observacoes(localEvento.getObservacoes())
                .build();
    }
}
