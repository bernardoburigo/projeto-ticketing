package com.dam.backend.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum RolesEnum {

    ROLE_ADMIN(1, "Admin"),
    ROLE_USUARIO(2, "Usuário"),
    ROLE_ORGANIZADOR(3, "Organizador");

    private final Integer id;
    private final String role;

    public static RolesEnum getById(Integer id) {
        return Stream.of(values())
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst().orElse(null);
    }

    public static String getRole(Integer id) {
        for (RolesEnum roles : RolesEnum.values()) {
            if (Objects.equals(roles.getId(), id)) {
                return roles.getRole();
            }
        }

        return null;
    }
}
