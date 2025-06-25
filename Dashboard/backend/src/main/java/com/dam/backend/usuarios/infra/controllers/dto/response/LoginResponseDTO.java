package com.dam.backend.usuarios.infra.controllers.dto.response;

public record LoginResponseDTO(String token, String expiresIn, String nome) {
}
