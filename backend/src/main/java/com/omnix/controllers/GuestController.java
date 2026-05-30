package com.omnix.controllers;

import com.omnix.dto.CheckInRequest;
import com.omnix.dto.CheckInResponse;
import com.omnix.dto.GuestResponse;
import com.omnix.dto.RsvpRequest;
import com.omnix.entities.User;
import com.omnix.services.GuestService;
import com.omnix.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    // POST /api/rsvp — público, convidado confirma sem login
    @PostMapping("/api/rsvp")
    public ResponseEntity<ApiResponse<GuestResponse>> confirmRsvp(
            @RequestBody @Valid RsvpRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.created("Presença confirmada com sucesso.",
                        guestService.confirmRsvp(request)));
    }

    // POST /api/checkin — equipe escaneia QR Code
    @PostMapping("/api/checkin")
    public ResponseEntity<ApiResponse<CheckInResponse>> checkIn(
            @RequestBody @Valid CheckInRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success("Check-in realizado.",
                        guestService.checkIn(request)));
    }

    // GET /api/events/{eventId}/guests — lista convidados do evento
    @GetMapping("/api/events/{eventId}/guests")
    public ResponseEntity<ApiResponse<List<GuestResponse>>> listGuests(
            @PathVariable Long eventId,
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(
                ApiResponse.success("Convidados encontrados.",
                        guestService.listByEvent(eventId)));
    }
}