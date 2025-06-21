package com.dam.backend.shared.utils;

import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;

public final class Util {

    private Util() {
        throw new ClasseNaoInstanciavelException();
    }

    public static String verifyStatusAtivo(Boolean ativo) {
        return ativo ? "Ativo" : "Inativo";
    }
}
