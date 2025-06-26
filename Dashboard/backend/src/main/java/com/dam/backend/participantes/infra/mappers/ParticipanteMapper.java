package com.dam.backend.participantes.infra.mappers;

import com.dam.backend.participantes.infra.controllers.dto.response.QrCodeResponseDTO;
import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import java.math.BigDecimal;

public final class ParticipanteMapper {

    private ParticipanteMapper() {
        throw new ClasseNaoInstanciavelException();
    }

    public static QrCodeResponseDTO toDTO(Integer id,
                                          String qrCode,
                                          String nomeEvento,
                                          BigDecimal valor) {
        return QrCodeResponseDTO.builder()
                .id(id)
                .qrCode(qrCode)
                .nomeEvento(nomeEvento)
                .valor(valor)
                .build();
    }
}
