package com.example.AppPublico.models;

public class VendaItemRequest {
    private Integer idLoteIngresso;
    private Integer quantidade;

    public VendaItemRequest(Integer idLoteIngresso, Integer quantidade) {
        this.idLoteIngresso = idLoteIngresso;
        this.quantidade = quantidade;
    }

    public Integer getIdLoteIngresso() {
        return idLoteIngresso;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
