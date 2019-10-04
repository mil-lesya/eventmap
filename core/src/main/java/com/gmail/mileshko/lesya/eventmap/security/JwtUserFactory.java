package com.gmail.mileshko.lesya.eventmap.security;

import com.gmail.mileshko.lesya.eventmap.entity.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getPassword()
        );
    }

}
