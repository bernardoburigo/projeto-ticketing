package com.example.AppPublico.models;

import java.io.Serializable;

public class CategoriaEvento implements Serializable {
    private int id;
    private String nome;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
