package com.dam.backend.shared.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ModelException.class)
    public ResponseEntity<Map<String, Object>> handleModelException(ModelException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("tipo", ex.getTipo());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
