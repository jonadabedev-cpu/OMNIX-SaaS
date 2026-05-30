package com.omnix.entities;

import com.omnix.enums.RsvpStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "convidados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Evento ao qual este convidado pertence
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    // QR Code único gerado após confirmação do RSVP
    @Column(columnDefinition = "TEXT", unique = true)
    private String qrCode;

    // Status da confirmação de presença
    @Enumerated(EnumType.STRING)
    @Column(name = "rsvp_status", nullable = false)
    @Builder.Default
    private RsvpStatus rsvpStatus = RsvpStatus.PENDING;

    // Se o convidado fez check-in no dia do evento
    @Column(name = "checked_in", nullable = false)
    @Builder.Default
    private Boolean checkedIn = false;

    // Horário exato do check-in via QR Code
    @Column(name = "checked_in_at")
    private LocalDateTime checkedInAt;
}
