package com.dam.backend.shared.enums;

import java.util.Objects;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaEventoEnum {

    SHOW(1, "show"),
    PALESTRA(2, "Palestra"),
    WORKSHOP(3, "WorkShop"),
    TEATRO(4, "Teatro"),
    FESTIVAL(5, "Festival"),
    ESPORTIVO(6, "Esportivo"),
    NETWORKING(7, "NetWorking"),
    BENEFICENTE(8, "Beneficente"),
    CONGRESSO(9, "Congresso"),
    FEIRA(10, "Feira"),
    EXPOSICAO(11, "Exposição"),
    TREINAMENTO(12, "Treinamento"),
    CONVENCAO(13, "Conveção"),
    REUNIAO(14, "Reunião"),
    MUSICAL(15, "Musical");

    private final Integer id;
    private final String categoria;

    public static CategoriaEventoEnum getById(Integer id) {
        return Stream.of(values())
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst().orElse(null);
    }

    public static String getCategoria(Integer id) {
        for (CategoriaEventoEnum categoria : CategoriaEventoEnum.values()) {
            if (Objects.equals(categoria.getId(), id)) {
                return categoria.getCategoria();
            }
        }

        return null;
    }
}
