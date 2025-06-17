package com.example.AppPublico.models;

public class Evento {
    private String nome;
    private String local;
    private String data;
    private String hora;
    private int imagemResId;
    private String artistas;

    public Evento(String nome, String local, String data, String hora, int imagemResId, String artistas) {
        this.nome = nome;
        this.local = local;
        this.data = data;
        this.hora = hora;
        this.imagemResId = imagemResId;
        this.artistas = artistas;
    }

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public int getImagemResId() {
        return imagemResId;
    }

    public String getArtistas() {
        return artistas;
    }
}
