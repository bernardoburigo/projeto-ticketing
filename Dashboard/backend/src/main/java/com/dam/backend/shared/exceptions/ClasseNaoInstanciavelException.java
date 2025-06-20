package com.dam.backend.shared.exceptions;

import com.dam.backend.shared.enums.HttpType;

public class ClasseNaoInstanciavelException extends ModelException {

    public ClasseNaoInstanciavelException() {
        super("Esta classe não deve ser instanciada.", HttpType.ERRO);
    }
}
