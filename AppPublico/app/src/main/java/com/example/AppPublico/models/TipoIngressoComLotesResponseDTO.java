package com.example.AppPublico.models;

import java.util.List;

public class TipoIngressoComLotesResponseDTO {
    private String nome;
    private List<LoteIngresso> lotes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LoteIngresso> getLotes() {
        return lotes;
    }

    public void setLotes(List<LoteIngresso> lotes) {
        this.lotes = lotes;
    }
}
