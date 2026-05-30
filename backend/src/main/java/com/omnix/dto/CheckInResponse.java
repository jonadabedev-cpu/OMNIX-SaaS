package com.omnix.dto;

public record CheckInResponse(
        String status,
        String guest,
        Boolean checkedIn,
        String time
) {}