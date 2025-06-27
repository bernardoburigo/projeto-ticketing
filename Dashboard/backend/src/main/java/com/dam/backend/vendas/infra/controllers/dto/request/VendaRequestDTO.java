package com.dam.backend.vendas.infra.controllers.dto.request;

import java.util.List;

public record VendaRequestDTO(List<VendaItemRequestDTO> itens) {
}
