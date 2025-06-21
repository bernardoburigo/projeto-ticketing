package com.dam.backend.usuarios.infra.controllers.dto.request;

public record UsuarioRequestDTO(String nome, String email, String senha, Integer role) {
}
