package com.gmail.mileshko.lesya.eventmap.repository;

import com.gmail.mileshko.lesya.eventmap.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByToken(String token);


}
