package com.gmail.mileshko.lesya.eventmap.dto;

public class MarkerDto {

    public Float latitude;
    public Float longitude;
    public UserDto user;
    public EventDto event;


    public MarkerDto() {
    }


    public MarkerDto(Float latitude, Float longitude, UserDto user, EventDto event) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
        this.event = event;
    }
}
