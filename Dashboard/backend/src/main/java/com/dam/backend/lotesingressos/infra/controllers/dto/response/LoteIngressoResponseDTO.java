package com.dam.backend.lotesingressos.infra.controllers.dto.response;

import com.dam.backend.eventos.infra.controllers.dto.response.LoteIngressoEventoResponseDTO;
import jakarta.annotation.Nullable;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record LoteIngressoResponseDTO(
        Integer id,
        String nome,
        LoteIngressoEventoResponseDTO evento,
        TipoIngressoResponseDTO tipoIngresso,
        BigDecimal preco,
        Integer quantidadeTotal,
        @Nullable Integer quantidadeVendida,
        String dataInicioVenda,
        String dataFinalVenda,
        String status) {
}
