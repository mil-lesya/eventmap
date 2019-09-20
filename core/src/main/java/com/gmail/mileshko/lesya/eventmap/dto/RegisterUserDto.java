package com.gmail.mileshko.lesya.eventmap.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegisterUserDto {

    @NotNull
    @NotEmpty
    public String name;

    @NotNull
    @NotEmpty
    public String surname;

    @NotNull
    @NotEmpty
    public String password;

    @NotNull
    @NotEmpty
    public String email;

    public RegisterUserDto() {
    }

    public RegisterUserDto(@NotNull @NotEmpty String name, @NotNull @NotEmpty String surname, @NotNull @NotEmpty String password, @NotNull @NotEmpty String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }
}
