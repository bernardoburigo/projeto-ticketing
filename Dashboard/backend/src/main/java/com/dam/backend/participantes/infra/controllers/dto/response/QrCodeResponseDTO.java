package com.dam.backend.participantes.infra.controllers.dto.response;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record QrCodeResponseDTO(Integer id, String qrCode, String nomeEvento, BigDecimal valor) {
}
