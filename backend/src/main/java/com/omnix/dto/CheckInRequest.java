package com.omnix.dto;

import jakarta.validation.constraints.NotBlank;

public record CheckInRequest(

        @NotBlank(message = "QR Code obrigatório")
        String qrCode
) {}