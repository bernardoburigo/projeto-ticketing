package com.dam.backend.shared.exceptions;

import com.dam.backend.shared.enums.HttpType;
import lombok.Getter;

@Getter
public class ModelException extends RuntimeException {

    public final HttpType tipo;

    public ModelException(String message, HttpType tipo) {
        super(message);
        this.tipo = tipo;
    }

    public ModelException(String message) {
        super(message);
        this.tipo = HttpType.ERRO;
    }
}
