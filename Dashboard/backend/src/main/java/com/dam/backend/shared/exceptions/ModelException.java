package com.dam.backend.shared.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ModelException extends RuntimeException {

    public final HttpStatus tipo;

    public ModelException(String message, HttpStatus tipo) {
        super(message);
        this.tipo = tipo;
    }

    public ModelException(String message) {
        super(message);
        this.tipo = HttpStatus.NOT_FOUND;
    }
}
