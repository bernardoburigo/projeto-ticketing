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
@Table(name = "pagamentos")
@Getter
@Setter
public class PagamentoEntity extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "i_pagamento")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_venda", nullable = false)
    private VendaEntity venda;

    @Column(name = "metodo_pagamento", nullable = false)
    private Integer metodoPagamento;

    @Column(name = "status_pagamento", nullable = false)
    private Integer statusPagamento;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "qr_code", columnDefinition = "TEXT")
    private String qrCode;

    @Column(name = "url_boleto", columnDefinition = "TEXT")
    private String urlBoleto;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "data_pagamento")
    private ZonedDateTime dataPagamento;
}
