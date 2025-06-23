package com.dam.backend.shared.enums;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoteIngressoEnum {

    PRIMEIRO_LOTE(1, "1° lote"),
    SEGUNDO_LOTE(2, "2° lote"),
    TECEIRO_LOTE(3, "3° lote"),
    QUARTO_LOTE(4, "4° lote"),
    QUINTO_LOTE(5, "5° lote");

    private final Integer id;
    private final String lote;

    public static LoteIngressoEnum getById(Integer id) {
        return Stream.of(values())
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst().orElse(null);
    }

    public static String getLote(Integer id) {
        for (LoteIngressoEnum lote : LoteIngressoEnum.values()) {
            if (Objects.equals(lote.getId(), id)) {
                return lote.getLote();
            }
        }

        return null;
    }
}
