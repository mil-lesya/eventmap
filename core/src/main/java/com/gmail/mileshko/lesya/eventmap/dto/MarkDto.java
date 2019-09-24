package com.gmail.mileshko.lesya.eventmap.dto;

import com.gmail.mileshko.lesya.eventmap.entity.User;

public class MarkDto {
    public Float latitude;
    public Float longitude;
    public User user;


    public MarkDto() {
    }

    public MarkDto(Float latitude, Float longitude, User user) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }
}
