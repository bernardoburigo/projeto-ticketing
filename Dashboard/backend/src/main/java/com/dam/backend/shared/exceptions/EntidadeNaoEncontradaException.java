package com.dam.backend.shared.exceptions;

import com.dam.backend.shared.enums.HttpType;

public class EntidadeNaoEncontradaException extends ModelException {
    public EntidadeNaoEncontradaException(String message) {
        super(message, HttpType.ERRO);
    }

    public EntidadeNaoEncontradaException(String message, HttpType httpType) {
        super(message, httpType);
    }
}
