package com.omnix.services;

import com.omnix.dto.EventRequest;
import com.omnix.dto.EventResponse;
import com.omnix.entities.User;

import java.util.List;

public interface EventService {
    EventResponse create(EventRequest request, User user);
    EventResponse update(Long id, EventRequest request, User user);
    void delete(Long id, User user);
    List<EventResponse> listByUser(User user);
    EventResponse findBySlug(String slug);
}