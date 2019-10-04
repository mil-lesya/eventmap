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
import com.gmail.mileshko.lesya.eventmap.security.JwtTokenUtil;
import com.gmail.mileshko.lesya.eventmap.security.JwtUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService detailsService;
    private final JwtTokenUtil jwtTokenUtil;


    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, TokenRepository tokenRepository, AuthenticationManager authenticationManager, JwtUserDetailsService detailsService, JwtTokenUtil jwtTokenUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.detailsService = detailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String authenticate(AuthUserDto authUserDto) throws AuthenticationException, NoSuchEntityException {

        User user = userRepository.findByEmail(authUserDto.email)
                .orElseThrow(() -> new NoSuchEntityException("no user with such mail"));

        if (!passwordEncoder.matches(authUserDto.password, user.getPassword()))
            throw new AuthenticationException("invalid user password");

        final UserDetails userDetails = detailsService.loadUserByUsername(authUserDto.email);
        Token token = new Token(user, jwtTokenUtil.generateToken(userDetails));

        return tokenRepository.save(token).getToken();
    }

    public Optional findByToken(String token) {
        Optional<Token> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isPresent()) {
            User user = optionalToken.get().getUser();
            org.springframework.security.core.userdetails.User authUser = new
                    org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return Optional.empty();

    }

    public User validate(String tokenValue) throws NoSuchEntityException {
        return tokenRepository.findByToken(tokenValue)
                .orElseThrow(() -> new NoSuchEntityException("no such token"))
                .getUser();
    }

    public boolean isAuth(String tokenValue) {
        return tokenRepository.findByToken(tokenValue).isPresent();
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
}
