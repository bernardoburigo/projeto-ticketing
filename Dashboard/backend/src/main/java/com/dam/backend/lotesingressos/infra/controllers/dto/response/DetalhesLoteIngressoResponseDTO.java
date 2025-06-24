package com.dam.backend.lotesingressos.infra.controllers.dto.response;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record DetalhesLoteIngressoResponseDTO(
        Integer id,
        String nome,
        BigDecimal preco,
        String dataInicioVenda,
        String dataFinalVenda,
        Integer quantidadeTotal,
        Integer quantidadeVendida,
        String status) {
}
