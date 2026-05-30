package com.omnix.controllers;

import com.omnix.dto.EventRequest;
import com.omnix.dto.EventResponse;
import com.omnix.entities.User;
import com.omnix.services.EventService;
import com.omnix.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    // POST /api/events
    @PostMapping
    public ResponseEntity<ApiResponse<EventResponse>> create(
            @RequestBody @Valid EventRequest request,
            @AuthenticationPrincipal User user) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.created("Evento criado com sucesso.",
                        eventService.create(request, user)));
    }

    // GET /api/events
    @GetMapping
    public ResponseEntity<ApiResponse<List<EventResponse>>> listMyEvents(
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(
                ApiResponse.success("Eventos encontrados.",
                        eventService.listByUser(user)));
    }

    // PUT /api/events/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponse>> update(
            @PathVariable Long id,
            @RequestBody @Valid EventRequest request,
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(
                ApiResponse.success("Evento atualizado.",
                        eventService.update(id, request, user)));
    }

    // DELETE /api/events/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {

        eventService.delete(id, user);
        return ResponseEntity.ok(
                ApiResponse.success("Evento deletado com sucesso."));
    }

    // GET /api/public/events/{slug} — rota pública
    @GetMapping("/public/{slug}")
    public ResponseEntity<ApiResponse<EventResponse>> getPublicEvent(
            @PathVariable String slug) {

        return ResponseEntity.ok(
                ApiResponse.success("Evento encontrado.",
                        eventService.findBySlug(slug)));
    }
}