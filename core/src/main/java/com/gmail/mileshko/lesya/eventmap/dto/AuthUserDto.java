package com.gmail.mileshko.lesya.eventmap.dto;

public class AuthUserDto {

    public String email;

    public String password;

    public AuthUserDto() {
    }

    public AuthUserDto(String email) {
        this.email = email;
    }

    public AuthUserDto(String mail, String password) {
        this.email = mail;
        this.password = password;
    }
}
