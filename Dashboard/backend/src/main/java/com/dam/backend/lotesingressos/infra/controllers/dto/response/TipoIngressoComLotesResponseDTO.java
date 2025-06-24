package com.dam.backend.lotesingressos.infra.controllers.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record TipoIngressoComLotesResponseDTO(
        TipoIngressoResponseDTO tipoIngresso,
        List<DetalhesLoteIngressoResponseDTO> lotes) {
}
