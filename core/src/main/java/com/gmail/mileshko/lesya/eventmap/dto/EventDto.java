package com.gmail.mileshko.lesya.eventmap.dto;

import com.gmail.mileshko.lesya.eventmap.entity.Marker;

import  java.sql.Timestamp;

public class EventDto {

    public Timestamp date;
    public String description;
    public String name;
    public Marker marker;

    public EventDto() {
    }

    public EventDto(Timestamp date, String description, String name, Marker marker) {
        this.date = date;
        this.description = description;
        this.name = name;
        this.marker = marker;
    }
}
