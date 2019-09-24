package com.gmail.mileshko.lesya.eventmap.repository;

import com.gmail.mileshko.lesya.eventmap.entity.Mark;
import com.gmail.mileshko.lesya.eventmap.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
    List<Mark> findAll();
}
