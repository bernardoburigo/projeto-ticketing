package com.dam.backend.vendas.infra.controllers.dto.request;

public record VendaItemRequestDTO(
        Integer idLoteIngresso,
        Integer quantidade
) {
}
