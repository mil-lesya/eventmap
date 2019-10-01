package com.gmail.mileshko.lesya.eventmap.repository;

import com.gmail.mileshko.lesya.eventmap.entity.Marker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkerRepository extends CrudRepository<Marker, Long> {
    List<Marker> findAll();

    Optional<Marker> findByLatitudeAndLongitude(Float latitude, Float longitude);
}
