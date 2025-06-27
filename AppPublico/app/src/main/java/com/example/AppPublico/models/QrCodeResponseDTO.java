package com.example.AppPublico.models;

import java.io.Serializable;

public class QrCodeResponseDTO implements Serializable {
    private Integer id;
    private String nome;
    private String documento;
    private String ingressoQrCode;
    private boolean checkin;

    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public String getDocumento() { return documento; }
    public String getIngressoQrCode() { return ingressoQrCode; }
    public boolean isCheckin() { return checkin; }
}
