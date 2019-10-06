package com.gmail.mileshko.lesya.eventmap.controller;

import com.gmail.mileshko.lesya.eventmap.dto.EventDto;
import com.gmail.mileshko.lesya.eventmap.dto.MarkerDto;
import com.gmail.mileshko.lesya.eventmap.entity.Event;
import com.gmail.mileshko.lesya.eventmap.entity.User;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.service.EventService;
import com.gmail.mileshko.lesya.eventmap.service.UserService;
import com.gmail.mileshko.lesya.eventmap.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("event")
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }


    @PostMapping("create")
    public void createEvent(@RequestHeader("token") String token, @RequestBody EventDto eventDto) throws NoSuchEntityException {
        User user = userService.validate(token);
        eventService.createEvent(eventDto);
    }

    @PostMapping("get")
    public EventDto getEvent( @RequestBody MarkerDto markerDto) throws NoSuchEntityException {
        Event event = eventService.getEvent(markerDto);
        return Mapper.map(event, EventDto.class);
    }
}
