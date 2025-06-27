package com.example.AppPublico.network;

public class LoginResponse {
    private String token;
    private String nome;
    private Integer idUsuario;

    public String getToken() {
        return token;
    }
    public String getNome() { return nome; }

    public Integer getIdUsuario() { return idUsuario;}
}