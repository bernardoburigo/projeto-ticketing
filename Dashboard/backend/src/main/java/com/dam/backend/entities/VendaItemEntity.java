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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vendas_itens")
@Getter
@Setter
public class VendaItemEntity extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "i_venda_item")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_venda", nullable = false)
    private VendaEntity venda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_lote_ingresso", nullable = false)
    private LoteIngressoEntity loteIngressoEntity;

    @Column(nullable = false)
    private Integer qauntidade;

    @Column(name = "valor_unitario", nullable = false)
    private Double valorUnitario;
}
