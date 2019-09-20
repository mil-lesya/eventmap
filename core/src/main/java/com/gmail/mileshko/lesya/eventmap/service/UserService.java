package com.gmail.mileshko.lesya.eventmap.service;

import com.gmail.mileshko.lesya.eventmap.dto.AuthUserDto;
import com.gmail.mileshko.lesya.eventmap.dto.RegisterUserDto;
import com.gmail.mileshko.lesya.eventmap.entity.Token;
import com.gmail.mileshko.lesya.eventmap.entity.User;
import com.gmail.mileshko.lesya.eventmap.exception.AuthenticationException;
import com.gmail.mileshko.lesya.eventmap.exception.NoSuchEntityException;
import com.gmail.mileshko.lesya.eventmap.exception.RegistrationException;
import com.gmail.mileshko.lesya.eventmap.repository.TokenRepository;
import com.gmail.mileshko.lesya.eventmap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, TokenRepository tokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public String authenticate(AuthUserDto authUserDto) throws NoSuchEntityException, AuthenticationException {
        User user = userRepository.findByEmail(authUserDto.email)
                .orElseThrow(() -> new NoSuchEntityException("no user with such mail"));

        if (!passwordEncoder.matches(authUserDto.password, user.getHash()))
            throw new AuthenticationException("invalid user password");

        String token = UUID.randomUUID().toString();
        Token userToken = new Token(user, token);
        return tokenRepository.save(userToken).getToken();
    }

    public void register(RegisterUserDto registerUserDto) throws RegistrationException {
        if (userRepository.findByEmail(registerUserDto.email).isPresent())
            throw new RegistrationException("user with such mail already exist");

        User user = new User();

        user.setName(registerUserDto.name);
        user.setSurname(registerUserDto.surname);
        user.setEmail(registerUserDto.email);
        user.setHash(passwordEncoder.encode(registerUserDto.password));

        userRepository.save(user);
    }
}
