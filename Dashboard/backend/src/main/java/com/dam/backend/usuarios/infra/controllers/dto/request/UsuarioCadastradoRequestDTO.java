package com.dam.backend.usuarios.infra.controllers.dto.request;

public record UsuarioCadastradoRequestDTO(String nome, String email, String senha, Integer role) {
}
