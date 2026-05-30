package com.omnix.services;

import com.omnix.dto.EventRequest;
import com.omnix.dto.EventResponse;
import com.omnix.entities.Event;
import com.omnix.entities.User;
import com.omnix.exceptions.BusinessException;
import com.omnix.exceptions.ResourceNotFoundException;
import com.omnix.repositories.EventRepository;
import com.omnix.utils.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public EventResponse create(EventRequest request, User user) {

        // Valida se o plano permite o template escolhido
        if (!user.getPlan().canUseTemplate(request.template())) {
            throw new BusinessException(
                    "Seu plano " + user.getPlan().getDisplayName() +
                    " permite apenas os templates 1 a " +
                    user.getPlan().getMaxTemplates() + "."
            );
        }

        String slug = SlugUtil.generate(request.title());
        if (eventRepository.existsBySlug(slug)) {
            slug = SlugUtil.generateUnique(request.title());
        }

        Event event = Event.builder()
                .user(user)
                .title(request.title())
                .slug(slug)
                .description(request.description())
                .eventDate(request.eventDate())
                .eventTime(request.eventTime())
                .address(request.address())
                .bannerUrl(request.bannerUrl())
                .type(request.type())
                .template(request.template())
                .build();

        return EventResponse.from(eventRepository.save(event));
    }

    @Override
    public EventResponse update(Long id, EventRequest request, User user) {
        Event event = findEventByIdAndUser(id, user);

        // Valida se o plano permite o template escolhido
        if (!user.getPlan().canUseTemplate(request.template())) {
            throw new BusinessException(
                    "Seu plano " + user.getPlan().getDisplayName() +
                    " permite apenas os templates 1 a " +
                    user.getPlan().getMaxTemplates() + "."
            );
        }

        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setEventDate(request.eventDate());
        event.setEventTime(request.eventTime());
        event.setAddress(request.address());
        event.setBannerUrl(request.bannerUrl());
        event.setType(request.type());
        event.setTemplate(request.template());

        return EventResponse.from(eventRepository.save(event));
    }

    @Override
    public void delete(Long id, User user) {
        Event event = findEventByIdAndUser(id, user);
        eventRepository.delete(event);
    }

    @Override
    public List<EventResponse> listByUser(User user) {
        return eventRepository.findByUser(user)
                .stream()
                .map(EventResponse::from)
                .toList();
    }

    @Override
    public EventResponse findBySlug(String slug) {
        return eventRepository.findBySlug(slug)
                .map(EventResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento não encontrado."));
    }

    // Método privado reutilizado no update e delete
    private Event findEventByIdAndUser(Long id, User user) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Evento não encontrado."));
        if (!event.getUser().getId().equals(user.getId())) {
            throw new BusinessException(
                    "Você não tem permissão para este evento.");
        }
        return event;
    }
}