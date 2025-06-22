package com.dam.backend.entities;

import com.dam.backend.entities.utils.Auditoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "participantes")
@Getter
@Setter
public class ParticipanteEntity extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "i_participante")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_venda_item", nullable = false)
    private VendaItemEntity vendaItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_usuario")
    private UsuarioEntity usuario;

    @Column
    private String nome;

    @Column
    private String documento;

    @Column(name = "ingresso_qrcode", nullable = false, columnDefinition = "TEXT")
    private String ingressoQrCode;

    @Column(nullable = false)
    private boolean checkin;

    @Column(name = "data_checkin", nullable = false)
    private ZonedDateTime dataCheckin;
}
