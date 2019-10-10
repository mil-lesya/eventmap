package com.gmail.mileshko.lesya.eventmap.dto;

import java.sql.Timestamp;

public class EventDto {

    public Long id;
    public String name;
    public Timestamp date;
    public String description;

    public EventDto() {
    }

    public EventDto(Long id, String name, Timestamp date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
    }
}
