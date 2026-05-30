package com.omnix.utils;

import java.time.LocalDateTime;

/**
 * Wrapper padrão para respostas de sucesso da API.
 *
 * Exemplo de retorno:
 * {
 *   "timestamp": "2026-05-29T22:00:00",
 *   "status": 200,
 *   "message": "Evento criado com sucesso",
 *   "data": { ...objeto... }
 * }
 */
public record ApiResponse<T>(
        String timestamp,
        Integer status,
        String message,
        T data
) {
    // Resposta de sucesso com dados
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(
                LocalDateTime.now().toString(),
                200,
                message,
                data
        );
    }

    // Resposta de sucesso sem dados (ex: delete)
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(
                LocalDateTime.now().toString(),
                200,
                message,
                null
        );
    }

    // Resposta de criação (201)
    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(
                LocalDateTime.now().toString(),
                201,
                message,
                data
        );
    }
}