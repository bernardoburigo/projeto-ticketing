package com.dam.backend.locaiseventos.infra.controllers.dto.response;

import lombok.Builder;

@Builder
public record LocaisEventoResponseDTO(
        Integer id,
        String nome,
        String endereco,
        String cidade,
        String estado,
        String cep,
        Integer capacidate,
        String observacoes
) {
}
