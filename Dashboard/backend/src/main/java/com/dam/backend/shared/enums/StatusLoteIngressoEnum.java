package com.dam.backend.shared.enums;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusLoteIngressoEnum {

    FUTURA(1, "Futura"),
    DISPONIVEL(2, "Disponivel"),
    ESGOTADO(3, "Esgotado"),
    EXPIRADO(4, "Expirado"),
    INDISPONIVEL(5, "Indisponivel");

    private final Integer id;
    private final String statusLote;

    public static StatusLoteIngressoEnum getById(Integer id) {
        return Stream.of(values())
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst().orElse(null);
    }

    public static String getStatusLote(Integer id) {
        for (StatusLoteIngressoEnum lote : StatusLoteIngressoEnum.values()) {
            if (Objects.equals(lote.getId(), id)) {
                return lote.getStatusLote();
            }
        }

        return null;
    }
}
