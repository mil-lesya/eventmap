package com.gmail.mileshko.lesya.eventmap.controller;

import com.gmail.mileshko.lesya.eventmap.dto.UserDto;
import com.gmail.mileshko.lesya.eventmap.entity.User;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.service.UserService;
import com.gmail.mileshko.lesya.eventmap.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("get")
    public UserDto get(@RequestHeader("token") String token) throws NoSuchEntityException {
        User user = userService.validate(token);
        return Mapper.map(user, UserDto.class);
    }
}
