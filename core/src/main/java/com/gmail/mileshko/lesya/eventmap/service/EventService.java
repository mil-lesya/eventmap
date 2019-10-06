package com.gmail.mileshko.lesya.eventmap.service;

import com.gmail.mileshko.lesya.eventmap.dto.EventDto;
import com.gmail.mileshko.lesya.eventmap.dto.MarkerDto;
import com.gmail.mileshko.lesya.eventmap.entity.Event;
import com.gmail.mileshko.lesya.eventmap.entity.Marker;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.repository.EventRepository;
import com.gmail.mileshko.lesya.eventmap.repository.MarkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final MarkerRepository markerRepository;
    private final EventRepository eventRepository;

    @Autowired
    public EventService(MarkerRepository markerRepository, EventRepository eventRepository) {
        this.markerRepository = markerRepository;
        this.eventRepository = eventRepository;
    }

    public void createEvent(EventDto eventDto) throws NoSuchEntityException {

        Event event = new Event();

        Marker marker = markerRepository.findById(eventDto.marker.getId())
                .orElseThrow(()-> new NoSuchEntityException("no such marker"));

        event.setMarker(marker);
        event.setName(eventDto.name);
        event.setDate(eventDto.date);
        event.setDescription(eventDto.description);


        eventRepository.save(event);
    }

    public Event getEvent(MarkerDto markerDto) throws NoSuchEntityException {

        Marker marker = markerRepository.findById(markerDto.id)
                .orElseThrow(()-> new NoSuchEntityException("no such marker"));

        return eventRepository.findByMarker(marker)
                .orElseThrow(() -> new NoSuchEntityException("no such event"));
    }
}
