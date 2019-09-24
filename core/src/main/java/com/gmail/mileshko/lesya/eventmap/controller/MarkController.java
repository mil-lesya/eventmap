package com.gmail.mileshko.lesya.eventmap.controller;

import com.gmail.mileshko.lesya.eventmap.dto.MarkDto;
import com.gmail.mileshko.lesya.eventmap.entity.User;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.service.MarkService;
import com.gmail.mileshko.lesya.eventmap.service.UserService;
import com.gmail.mileshko.lesya.eventmap.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mark")
public class MarkController {

    private final MarkService markService;
    private final UserService userService;

    @Autowired
    public MarkController(MarkService markService, UserService userService) {
        this.markService = markService;
        this.userService = userService;
    }

    @PostMapping("save")
    public void saveMark(@RequestBody MarkDto markDto, @RequestHeader("token") String token) throws NoSuchEntityException {
        User user = userService.validate(token);
        markService.saveMark(markDto);
    }

    @GetMapping("get")
    public List<MarkDto> getMarks(@RequestHeader("token") String token) throws NoSuchEntityException {
        User user = userService.validate(token);
        return Mapper.mapAll(markService.getMarks(), MarkDto.class);
    }
}
