package com.omnix.repositories;

import com.omnix.entities.Event;
import com.omnix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Lista todos os eventos de um usuário — usado no dashboard
    List<Event> findByUser(User user);

    // Busca evento pela URL pública — usado na página pública
    Optional<Event> findBySlug(String slug);

    // Verifica se slug já existe — evita URLs duplicadas
    boolean existsBySlug(String slug);
}
