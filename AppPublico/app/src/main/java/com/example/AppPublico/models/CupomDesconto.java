package com.example.AppPublico.models;

public class CupomDesconto {
    private String codigo;
    private double desconto; // Ex: 0.20 = 20%

    public CupomDesconto(String codigo, double desconto) {
        this.codigo = codigo;
        this.desconto = desconto;
    }

    public String getCodigo() { return codigo; }
    public double getDesconto() { return desconto; }

    public double aplicarDesconto(double valorOriginal) {
        return valorOriginal * (1 - desconto);
    }
}
