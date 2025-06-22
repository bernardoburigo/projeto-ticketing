package com.dam.backend.shared.enums;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetodoPagamentoEnum {

    PIX(1, "Pix"),
    CARTAO_DEBITO(2, "Cartão débito"),
    CARTAO_CREDITO(3, "Cartão crédito"),
    BOLETO(4, "Boleto");

    private final Integer id;
    private final String metodoPagamento;

    public static MetodoPagamentoEnum getById(Integer id) {
        return Stream.of(values())
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst().orElse(null);
    }

    public static String getStatus(Integer id) {
        for (MetodoPagamentoEnum metodoPagamento : MetodoPagamentoEnum.values()) {
            if (Objects.equals(metodoPagamento.getId(), id)) {
                return metodoPagamento.getMetodoPagamento();
            }
        }

        return null;
    }
}
