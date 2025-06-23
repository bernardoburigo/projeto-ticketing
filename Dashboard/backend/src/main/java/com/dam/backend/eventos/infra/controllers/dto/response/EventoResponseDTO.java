package com.dam.backend.eventos.infra.controllers.dto.response;

import com.dam.backend.locaiseventos.infra.controllers.dto.response.LocaisEventoResponseDTO;
import com.dam.backend.usuarios.infra.controllers.dto.response.OrganizadorResponseDTO;
import lombok.Builder;

@Builder
public record EventoResponseDTO(
        Integer id,
        String nome,
        String descricao,
        String dataInicio,
        String dataFinal,
        LocaisEventoResponseDTO localEvento,
        CategoriaEventoResponseDTO categoria,
        OrganizadorResponseDTO organizador,
        String statusEvento) {
}
