package com.gmail.mileshko.lesya.eventmap.repository;

import com.gmail.mileshko.lesya.eventmap.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String mail);
}
