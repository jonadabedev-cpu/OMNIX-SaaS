package com.omnix.services;

import com.omnix.dto.CheckInRequest;
import com.omnix.dto.CheckInResponse;
import com.omnix.dto.GuestResponse;
import com.omnix.dto.RsvpRequest;

import java.util.List;

public interface GuestService {
    GuestResponse confirmRsvp(RsvpRequest request);
    CheckInResponse checkIn(CheckInRequest request);
    List<GuestResponse> listByEvent(Long eventId);
}