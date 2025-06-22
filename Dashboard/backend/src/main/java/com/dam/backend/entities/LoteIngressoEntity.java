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
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lotes_ingresso")
@Getter
@Setter
public class LoteIngressoEntity extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "i_lote_ingresso")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_evento", nullable = false)
    private EventosEntity evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_tipo_ingresso", nullable = false)
    private TipoIngressoEntity tipoIngresso;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(name = "quantidade_total", nullable = false)
    private Integer quantidadeTotal;

    @Column(name = "quantidade_vendida", nullable = false)
    private Integer quantidadeVendida;

    @Column(name = "data_inicio_venda", nullable = false)
    private ZonedDateTime dataInicioVenda;

    @Column(name = "data_final_venda", nullable = false)
    private ZonedDateTime dataFinalVenda;
}
