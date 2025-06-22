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
@Getter
@Setter
@Table(name = "eventos")
public class EventosEntity extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "i_evento")
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private ZonedDateTime dataInicio;

    @Column(name = "data_final", nullable = false)
    private ZonedDateTime dataFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_local_evento", nullable = false)
    private LocalEventoEntity localEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_categoria_evento", nullable = false)
    private CategoriaEventoEntity categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_usuario", nullable = false)
    private UsuarioEntity organizador;

    @Column(nullable = false)
    private Integer status;
}
