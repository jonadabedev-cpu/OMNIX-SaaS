package com.omnix.dto;

import com.omnix.entities.Event;
import com.omnix.enums.EventType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record EventResponse(
        Long id,
        String title,
        String slug,
        String description,
        LocalDate eventDate,
        LocalTime eventTime,
        String address,
        String bannerUrl,
        EventType type,
        Integer template, // retorna o template para o Angular renderizar
        LocalDateTime createdAt
) {
    public static EventResponse from(Event event) {
        return new EventResponse(
                event.getId(),
                event.getTitle(),
                event.getSlug(),
                event.getDescription(),
                event.getEventDate(),
                event.getEventTime(),
                event.getAddress(),
                event.getBannerUrl(),
                event.getType(),
                event.getTemplate(),
                event.getCreatedAt()
        );
    }
}