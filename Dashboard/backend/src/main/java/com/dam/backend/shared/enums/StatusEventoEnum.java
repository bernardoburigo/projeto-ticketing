package com.dam.backend.shared.enums;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEventoEnum {

    ATIVO(1, "Ativo"),
    ENCERRADO(2, "Encerrado"),
    CANCELADO(3, "Cancelado");

    private final Integer id;
    private final String status;

    public static StatusEventoEnum getById(Integer id) {
        return Stream.of(values())
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst().orElse(null);
    }

    public static String getStatus(Integer id) {
        for (StatusEventoEnum status : StatusEventoEnum.values()) {
            if (Objects.equals(status.getId(), id)) {
                return status.getStatus();
            }
        }

        return null;
    }
}
