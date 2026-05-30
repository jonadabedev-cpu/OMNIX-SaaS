package com.omnix.repositories;

import com.omnix.entities.Event;
import com.omnix.entities.GiftList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftListRepository extends JpaRepository<GiftList, Long> {

    // Lista todos os presentes de um evento
    List<GiftList> findByEvent(Event event);
}
