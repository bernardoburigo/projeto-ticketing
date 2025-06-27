package com.example.AppPublico.models;

import java.util.List;

public class VendaRequest {
    private List<VendaItemRequest> itens;

    public VendaRequest(List<VendaItemRequest> itens) {
        this.itens = itens;
    }

    public List<VendaItemRequest> getItens() {
        return itens;
    }
}
