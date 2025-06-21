package com.dam.backend.shared;

import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;

public final class Permissoes {

    private Permissoes() {
        throw new ClasseNaoInstanciavelException();
    }

    public static final String DEFAULT = "DEFAULT";
}
