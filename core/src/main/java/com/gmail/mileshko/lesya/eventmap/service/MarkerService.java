package com.gmail.mileshko.lesya.eventmap.service;

import com.gmail.mileshko.lesya.eventmap.dto.MarkerDto;
import com.gmail.mileshko.lesya.eventmap.dto.SelectedMarkerDto;
import com.gmail.mileshko.lesya.eventmap.entity.Marker;
import com.gmail.mileshko.lesya.eventmap.entity.User;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.repository.MarkerRepository;
import com.gmail.mileshko.lesya.eventmap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MarkerService {

    private final MarkerRepository markerRepository;
    private final UserRepository userRepository;

    @Autowired
    public MarkerService(MarkerRepository markerRepository, UserRepository userRepository) {
        this.markerRepository = markerRepository;
        this.userRepository = userRepository;
    }

    public void saveMark(MarkerDto markerDto) throws NoSuchEntityException {

        Marker marker = new Marker();

        User user = userRepository.findById(markerDto.user.getId())
                .orElseThrow(() -> new NoSuchEntityException("user not found."));

        marker.setUser(user);
        marker.setLongitude(markerDto.longitude);
        marker.setLatitude(markerDto.latitude);

        markerRepository.save(marker);
    }

    public List<Marker> getMarks() {
        return markerRepository.findAll();
    }

    public void deleteMark(MarkerDto markerDto, User user) throws NoSuchEntityException {

        Marker marker = markerRepository.findByLatitudeAndLongitude(markerDto.latitude, markerDto.longitude)
                .orElseThrow(() -> new NoSuchEntityException("mark not found"));

        if (user.getId().equals(markerDto.user.getId())) {
            markerRepository.delete(marker);
        }

    }

    public Marker getMarker(SelectedMarkerDto selectedMarkerDto) throws NoSuchEntityException {

        return markerRepository.findByLatitudeAndLongitude(selectedMarkerDto.latitude, selectedMarkerDto.longitude)
                .orElseThrow(() ->  new NoSuchEntityException("mark not found"));
    }
}
