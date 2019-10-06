package com.gmail.mileshko.lesya.eventmap.repository;

import com.gmail.mileshko.lesya.eventmap.entity.Event;
import com.gmail.mileshko.lesya.eventmap.entity.Marker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Optional<Event> findByMarker(Marker marker);
}
