package com.omnix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Lança 400 para violações de regra de negócio
// Ex: RSVP duplicado, check-in duplicado, plano insuficiente
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}