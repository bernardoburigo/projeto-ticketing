package com.dam.backend.shared.exceptions;

import org.springframework.http.HttpStatus;

public class EntidadeNaoEncontradaException extends ModelException {
    public EntidadeNaoEncontradaException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public EntidadeNaoEncontradaException(String message, HttpStatus httpType) {
        super(message, httpType);
    }
}
