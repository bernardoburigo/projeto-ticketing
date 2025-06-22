package com.dam.backend.shared.exceptions;

import org.springframework.http.HttpStatus;

public class ClasseNaoInstanciavelException extends ModelException {

    public ClasseNaoInstanciavelException() {
        super("Esta classe não deve ser instanciada.", HttpStatus.NOT_FOUND);
    }
}
