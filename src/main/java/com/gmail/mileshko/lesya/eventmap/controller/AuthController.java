package com.gmail.mileshko.lesya.eventmap.controller;

import com.gmail.mileshko.lesya.eventmap.dto.AuthUserDto;
import com.gmail.mileshko.lesya.eventmap.exception.AuthenticationException;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String authenticate(@RequestBody AuthUserDto authUserDto) throws NoSuchEntityException, AuthenticationException {
        return userService.authenticate(authUserDto);
    }


}
