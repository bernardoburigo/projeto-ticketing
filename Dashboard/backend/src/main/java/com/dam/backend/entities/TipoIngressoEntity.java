package com.dam.backend.entities;

import com.dam.backend.entities.utils.Auditoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipos_ingressos")
@Getter
@Setter
public class TipoIngressoEntity extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "i_tipo_ingresso")
    private Integer id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}
