package com.example.AppPublico.models;

public class UsuarioRequest {
    private String nome;
    private String email;
    private String senha;
    private Integer role;

    public UsuarioRequest(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = null;
    }

}