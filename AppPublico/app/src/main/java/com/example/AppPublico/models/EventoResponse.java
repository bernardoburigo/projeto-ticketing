package com.example.AppPublico.models;

import java.util.List;

public class EventoResponse {
    private List<Evento> content;

    public List<Evento> getContent() {
        return content;
    }

    public void setContent(List<Evento> content) {
        this.content = content;
    }
}
