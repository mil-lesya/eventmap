package com.gmail.mileshko.lesya.eventmap.controller;

import com.gmail.mileshko.lesya.eventmap.dto.EventDto;
import com.gmail.mileshko.lesya.eventmap.dto.MarkerDto;
import com.gmail.mileshko.lesya.eventmap.entity.Event;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.service.EventService;
import com.gmail.mileshko.lesya.eventmap.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("create")
    public EventDto createEvent(@RequestBody EventDto eventDto) throws NoSuchEntityException {
        Event event = eventService.createEvent(eventDto);
        return Mapper.map(event, EventDto.class);
    }

}
