package com.dam.backend.usuarios.infra.controllers.exceptions;

import com.dam.backend.shared.enums.HttpType;
import com.dam.backend.shared.exceptions.ModelException;

public class LoginIncorretoException extends ModelException {
    public LoginIncorretoException() {
        super("Login inválido.", HttpType.ERRO);
    }
}
