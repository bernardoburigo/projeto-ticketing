package com.dam.backend.locaiseventos.infra.controllers.dto.request;

import jakarta.annotation.Nullable;

public record LocalEventoRequestDTO(
        String nome,
        @Nullable String endereco,
        String cidade,
        @Nullable String estado,
        @Nullable String cep,
        @Nullable Integer capacidade,
        @Nullable String obs) {
}
