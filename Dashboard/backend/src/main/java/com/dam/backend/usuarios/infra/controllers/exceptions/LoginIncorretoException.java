package com.dam.backend.usuarios.infra.controllers.exceptions;

import com.dam.backend.shared.exceptions.ModelException;
import org.springframework.http.HttpStatus;

public class LoginIncorretoException extends ModelException {
    public LoginIncorretoException() {
        super("Login inválido.", HttpStatus.NOT_FOUND);
    }
}
