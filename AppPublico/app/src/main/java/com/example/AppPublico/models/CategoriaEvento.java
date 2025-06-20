package com.example.AppPublico.models;

import java.util.List;

public class CategoriaEvento {
    private String nome;
    private List<Evento> eventos;

    public CategoriaEvento(String nome, List<Evento> eventos) {
        this.nome = nome;
        this.eventos = eventos;
    }

    public String getNome() {
        return nome;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
