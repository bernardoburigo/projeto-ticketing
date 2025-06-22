package com.dam.backend.shared.enums;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoIngressoEnum {

    VIP(1, "Vip"),
    PISTA(2, "Pista"),
    MEIA_ENTRADA(3, "Meia entrada"),
    INTEIRA(4, "Inteira"),
    ESPECIAL(5, "Especial");

    private final Integer id;
    private final String tipo;

    public static TipoIngressoEnum getById(Integer id) {
        return Stream.of(values())
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public static String getTipo(Integer id) {
        for (TipoIngressoEnum tipo : TipoIngressoEnum.values()) {
            if (Objects.equals(tipo.getId(), id)) {
                return tipo.getTipo();
            }
        }

        return null;
    }
}
