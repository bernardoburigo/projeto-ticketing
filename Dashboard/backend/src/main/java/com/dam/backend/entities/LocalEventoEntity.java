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
@Table(name = "locais_eventos")
@Getter
@Setter
public class LocalEventoEntity extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "i_local_evento")
    private Integer id;

    @Column(nullable = false)
    private String nome;

    private String endereco;

    @Column(nullable = false)
    private String cidade;

    private String estado;

    private String cep;

    private Integer capacidade;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
