package com.example.AppPublico.models;

public class Ingresso {
    private String idIngresso;
    private Evento evento;
    private String dataCompra;
    private double valorPago;
    private String qrCode;

    public Ingresso(String idIngresso, Evento evento, String dataCompra, double valorPago, String qrCode) {
        this.idIngresso = idIngresso;
        this.evento = evento;
        this.dataCompra = dataCompra;
        this.valorPago = valorPago;
        this.qrCode = qrCode;
    }

    public String getIdIngresso() { return idIngresso; }
    public Evento getEvento() { return evento; }
    public String getDataCompra() { return dataCompra; }
    public double getValorPago() { return valorPago; }
    public String getQrCode() { return qrCode; }
}
