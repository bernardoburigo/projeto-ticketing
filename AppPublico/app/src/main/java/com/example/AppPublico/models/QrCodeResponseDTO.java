package com.example.AppPublico.models;

import java.io.Serializable;
import java.math.BigDecimal; // Importar BigDecimal

public class QrCodeResponseDTO implements Serializable {
    private Integer id;
    private String qrCode;      // Deve corresponder ao campo do backend para os dados do QR Code
    private String nomeEvento;  // Para o nome do evento
    private BigDecimal valor;   // Para o valor do ingresso

    // Construtor vazio para GSON/Jackson
    public QrCodeResponseDTO() {
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getQrCode() { // Este é o campo que você usará para gerar a imagem do QR
        return qrCode;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    // Opcional: toString para debugging
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