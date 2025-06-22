package com.dam.backend.shared.enums;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPagamentoEnum {

    PAGO(1, "Pago"),
    PENDENTE(2, "Pendente"),
    CANCELADO(3, "Cancelado"),
    FALHOU(4, "Falhou");

    private final Integer id;
    private final String status;

    public static StatusPagamentoEnum getById(Integer id) {
        return Stream.of(values())
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst().orElse(null);
    }

    public static String getStatus(Integer id) {
        for (StatusPagamentoEnum status : StatusPagamentoEnum.values()) {
            if (Objects.equals(status.getId(), id)) {
                return status.getStatus();
            }
        }

        return null;
    }
}
