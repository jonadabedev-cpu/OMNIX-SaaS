package com.omnix.utils;

import java.time.LocalDateTime;

/**
 * Wrapper padrão para respostas de erro da API.
 *
 * Exemplo de retorno:
 * {
 *   "timestamp": "2026-05-29T22:00:00",
 *   "status": 404,
 *   "error": "Not Found",
 *   "message": "Evento não encontrado"
 * }
 */
public record ErrorResponse(
        String timestamp,
        Integer status,
        String error,
        String message
) {
    public static ErrorResponse of(Integer status, String error, String message) {
        return new ErrorResponse(
                LocalDateTime.now().toString(),
                status,
                error,
                message
        );
    }

    // Atalhos para os erros mais comuns
    public static ErrorResponse notFound(String message) {
        return of(404, "Not Found", message);
    }

    public static ErrorResponse badRequest(String message) {
        return of(400, "Bad Request", message);
    }

    public static ErrorResponse unauthorized(String message) {
        return of(401, "Unauthorized", message);
    }

    public static ErrorResponse internalError() {
        return of(500, "Internal Server Error", "Erro interno do servidor.");
    }
}