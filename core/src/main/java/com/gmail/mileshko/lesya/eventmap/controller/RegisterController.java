package com.gmail.mileshko.lesya.eventmap.controller;

import com.gmail.mileshko.lesya.eventmap.dto.RegisterUserDto;
import com.gmail.mileshko.lesya.eventmap.exception.RegistrationException;
import com.gmail.mileshko.lesya.eventmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void register(@RequestBody RegisterUserDto registerUserDto) throws RegistrationException {
        userService.register(registerUserDto);
    }

}