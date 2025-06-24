package com.dam.backend.lotesingressos.infra.controllers.dto.response;

import com.dam.backend.eventos.infra.controllers.dto.response.LoteIngressoEventoResponseDTO;
import java.util.List;
import lombok.Builder;

@Builder
public record PaginarLoteIngressoResponseDTO(
        LoteIngressoEventoResponseDTO evento,
        TipoIngressoResponseDTO tipoIngresso,
        List<DetalhesLoteIngressoResponseDTO> lotes) {
}
