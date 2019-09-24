package com.gmail.mileshko.lesya.eventmap.dto;

public class UserDto {

    public Long id;

    public String name;

    public String surname;

    public String email;

    public UserDto() {
    }

    public UserDto(Long id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
