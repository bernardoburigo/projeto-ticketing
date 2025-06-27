package com.example.AppPublico.models;

public class HistoricoIngresso {
    private String nomeEvento;
    private String data;
    private String local;
    private String hora;

    private String valorPago;

    public HistoricoIngresso(String nomeEvento, String data, String local, String hora, String valorPago) {
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.local = local;
        this.hora = hora;
        this.valorPago = valorPago;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public String getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }

    public String getHora() {
        return hora;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
