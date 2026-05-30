package com.omnix.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Table(name = "lista_presente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Evento ao qual este presente pertence
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    // Nome do presente: ex "Jogo de panelas", "Aspirador"
    @Column(nullable = false)
    private String title;

    // Link externo do produto (Amazon, Mercado Livre, etc)
    @Column(columnDefinition = "TEXT")
    private String link;
}