package com.gmail.mileshko.lesya.eventmap.dto;

import com.gmail.mileshko.lesya.eventmap.entity.User;

public class MarkerDto {

    public Long id;
    public Float latitude;
    public Float longitude;
    public User user;


    public MarkerDto() {
    }

    public MarkerDto(Long id, Float latitude, Float longitude, User user) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }
}
