package com.gmail.mileshko.lesya.eventmap.service;

import com.gmail.mileshko.lesya.eventmap.dto.EventDto;
import com.gmail.mileshko.lesya.eventmap.entity.Event;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService( EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(EventDto eventDto) throws NoSuchEntityException {
        Event event = new Event();

        event.setName(eventDto.name);
        event.setDate(eventDto.date);
        event.setDescription(eventDto.description);

        return eventRepository.save(event);
    }


}
