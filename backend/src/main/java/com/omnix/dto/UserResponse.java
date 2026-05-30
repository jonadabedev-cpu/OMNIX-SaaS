package com.omnix.dto;

import com.omnix.entities.User;
import com.omnix.enums.PlanType;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String email,
        PlanType plan,
        LocalDateTime createdAt
) {
    // Converte entidade → DTO de forma prática
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPlan(),
                user.getCreatedAt()
        );
    }
}