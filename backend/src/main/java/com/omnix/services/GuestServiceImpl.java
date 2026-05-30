package com.omnix.services;

import com.omnix.dto.CheckInRequest;
import com.omnix.dto.CheckInResponse;
import com.omnix.dto.GuestResponse;
import com.omnix.dto.RsvpRequest;
import com.omnix.entities.Event;
import com.omnix.entities.Guest;
import com.omnix.enums.RsvpStatus;
import com.omnix.exceptions.BusinessException;
import com.omnix.exceptions.ResourceNotFoundException;
import com.omnix.repositories.EventRepository;
import com.omnix.repositories.GuestRepository;
import com.omnix.utils.QrCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final EventRepository eventRepository;

    @Override
    public GuestResponse confirmRsvp(RsvpRequest request) {
        Event event = eventRepository.findById(request.eventId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento não encontrado."));

        // Regra: não permitir RSVP duplicado
        guestRepository.findByEmailAndEvent(request.email(), event)
                .ifPresent(g -> {
                    throw new BusinessException(
                            "Presença já confirmada para este email.");
                });

        String qrCode = QrCodeUtil.generate(event.getId());

        Guest guest = Guest.builder()
                .event(event)
                .name(request.guestName())
                .email(request.email())
                .phone(request.phone())
                .qrCode(qrCode)
                .rsvpStatus(RsvpStatus.CONFIRMED)
                .build();

        return GuestResponse.from(guestRepository.save(guest));
    }

    @Override
    public CheckInResponse checkIn(CheckInRequest request) {
        if (!QrCodeUtil.isValid(request.qrCode())) {
            throw new BusinessException("QR Code inválido.");
        }

        Guest guest = guestRepository.findByQrCode(request.qrCode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Convidado não encontrado."));

        // Regra: não permitir check-in duplicado
        if (guest.getCheckedIn()) {
            throw new BusinessException("Check-in já realizado anteriormente.");
        }

        guest.setCheckedIn(true);
        guest.setCheckedInAt(LocalDateTime.now());
        guestRepository.save(guest);

        return new CheckInResponse(
                "VALID",
                guest.getName(),
                true,
                guest.getCheckedInAt().toString()
        );
    }

    @Override
    public List<GuestResponse> listByEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento não encontrado."));
        return guestRepository.findByEvent(event)
                .stream()
                .map(GuestResponse::from)
                .toList();
    }
}