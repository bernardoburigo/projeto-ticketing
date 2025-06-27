package com.example.AppPublico.models;

import java.io.Serializable;

public class CategoriaEvento implements Serializable {
    private Integer id;
    private String nome;

    public CategoriaEvento(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
