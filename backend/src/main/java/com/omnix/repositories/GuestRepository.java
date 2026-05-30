package com.omnix.repositories;

import com.omnix.entities.Event;
import com.omnix.entities.Guest;
import com.omnix.enums.RsvpStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    // Lista todos os convidados de um evento
    List<Guest> findByEvent(Event event);

    // Busca convidado pelo QR Code — usado no check-in
    Optional<Guest> findByQrCode(String qrCode);

    // Busca convidado pelo email dentro de um evento — evita RSVP duplicado
    Optional<Guest> findByEmailAndEvent(String email, Event event);

    // Lista convidados por status — útil no dashboard
    List<Guest> findByEventAndRsvpStatus(Event event, RsvpStatus rsvpStatus);

    // Conta confirmados — útil no dashboard
    long countByEventAndRsvpStatus(Event event, RsvpStatus rsvpStatus);
}