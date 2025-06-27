package com.example.AppPublico.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class QrCodeResponseDTO implements Serializable {
    private Integer id;
    private String qrCode;
    private String nomeEvento;
    private BigDecimal valor;

    public QrCodeResponseDTO() {
    }

    public Integer getId() {
        return id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "QrCodeResponseDTO{" +
                "id=" + id +
                ", qrCode='" + qrCode + '\'' +
                ", nomeEvento='" + nomeEvento + '\'' +
                ", valor=" + valor +
                '}';
    }
}