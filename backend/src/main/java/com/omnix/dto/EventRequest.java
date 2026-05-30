package com.omnix.dto;

import com.omnix.enums.EventType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventRequest(

        @NotBlank(message = "Título obrigatório")
        String title,

        String description,

        @NotNull(message = "Data do evento obrigatória")
        @FutureOrPresent(message = "Data não pode ser no passado")
        LocalDate eventDate,

        LocalTime eventTime,
        String address,
        String bannerUrl,

        @NotNull(message = "Tipo do evento obrigatório")
        EventType type,

        // Template escolhido — validado contra o plano no service
        @NotNull(message = "Template obrigatório")
        @Min(value = 1, message = "Template mínimo é 1")
        @Max(value = 10, message = "Template máximo é 10")
        Integer template
) {}