package com.omnix.dto;

import com.omnix.entities.Guest;
import com.omnix.enums.RsvpStatus;

import java.time.LocalDateTime;

public record GuestResponse(
        Long id,
        String name,
        String email,
        String phone,
        String qrCode,
        RsvpStatus rsvpStatus,
        Boolean checkedIn,
        LocalDateTime checkedInAt
) {
    public static GuestResponse from(Guest guest) {
        return new GuestResponse(
                guest.getId(),
                guest.getName(),
                guest.getEmail(),
                guest.getPhone(),
                guest.getQrCode(),
                guest.getRsvpStatus(),
                guest.getCheckedIn(),
                guest.getCheckedInAt()
        );
    }
}