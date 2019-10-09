package com.gmail.mileshko.lesya.eventmap.service;

import com.gmail.mileshko.lesya.eventmap.dto.RegisterUserDto;
import com.gmail.mileshko.lesya.eventmap.entity.User;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.exception.RegistrationException;
import com.gmail.mileshko.lesya.eventmap.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.gmail.mileshko.lesya.eventmap.security.SecurityConstants.SECRET;
import static com.gmail.mileshko.lesya.eventmap.security.SecurityConstants.TOKEN_PREFIX;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void register(RegisterUserDto registerUserDto) throws RegistrationException {
        if (userRepository.findByEmail(registerUserDto.email).isPresent())
            throw new RegistrationException("user with such mail already exist");

        User user = new User();

        user.setName(registerUserDto.name);
        user.setSurname(registerUserDto.surname);
        user.setEmail(registerUserDto.email);
        user.setPassword(passwordEncoder.encode(registerUserDto.password));

        userRepository.save(user);
    }

    public  User getUser(String token) throws NoSuchEntityException {
        String email = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        return userRepository.findByEmail(email)
                .orElseThrow(()-> new NoSuchEntityException("no such user."));
    }
}
