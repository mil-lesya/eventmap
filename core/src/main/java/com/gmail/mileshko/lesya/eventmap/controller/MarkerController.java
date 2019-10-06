package com.gmail.mileshko.lesya.eventmap.controller;

import com.gmail.mileshko.lesya.eventmap.dto.MarkerDto;
import com.gmail.mileshko.lesya.eventmap.dto.SelectedMarkerDto;
import com.gmail.mileshko.lesya.eventmap.entity.Marker;
import com.gmail.mileshko.lesya.eventmap.entity.User;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.service.MarkerService;
import com.gmail.mileshko.lesya.eventmap.service.UserService;
import com.gmail.mileshko.lesya.eventmap.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("marker")
public class MarkerController {

    private final MarkerService markerService;
    private final UserService userService;

    @Autowired
    public MarkerController(MarkerService markerService, UserService userService) {
        this.markerService = markerService;
        this.userService = userService;
    }

    @PostMapping("save")
    public void saveMark(@RequestBody MarkerDto markerDto, @RequestHeader("token") String token) throws NoSuchEntityException {
        User user = userService.validate(token);
        markerService.saveMark(markerDto);
    }

    @GetMapping("get/all")
    public List<MarkerDto> getMarks() {
        return Mapper.mapAll(markerService.getMarks(), MarkerDto.class);
    }

    @PostMapping("get")
    public MarkerDto getMark(@RequestBody SelectedMarkerDto selectedMarkerDto) throws NoSuchEntityException {
        Marker marker = markerService.getMarker(selectedMarkerDto);
        return Mapper.map(marker, MarkerDto.class);
    }

    @PostMapping("delete")
    public void deleteMark(@RequestBody MarkerDto markerDto, @RequestHeader("token") String token) throws NoSuchEntityException {
        User user = userService.validate(token);
        markerService.deleteMark(markerDto, user);
    }
}
