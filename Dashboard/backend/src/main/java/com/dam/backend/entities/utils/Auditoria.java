package com.dam.backend.entities.utils;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public class Auditoria {

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false)
    private boolean excluido;

    @Column(nullable = false)
    private Integer versao;

    @Column(name = "aud_criado_data", nullable = false)
    private ZonedDateTime audCriadoData;

    @Column(name = "aud_modificado_data", nullable = false)
    private ZonedDateTime audModificadoData;

    @PrePersist
    public void createRegister() {
        this.ativo = true;
        this.excluido = false;
        this.versao = 1;
        this.audCriadoData = ZonedDateTime.now();
        this.audModificadoData = ZonedDateTime.now();
    }

    @PreUpdate
    public void updateRegister() {
        this.versao = this.versao + 1;
        this.audModificadoData = ZonedDateTime.now();
    }
}
