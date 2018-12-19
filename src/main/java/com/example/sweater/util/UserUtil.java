package com.example.sweater.util;

import com.example.sweater.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserUtil {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String checkMatchesPasswords(CharSequence password, String encodePassword) {
        String lastEncodePassword = encodePassword;
        if (!passwordEncoder.matches(password, encodePassword)) {
            lastEncodePassword = passwordEncoder.encode(password);
        }
        return lastEncodePassword;
    }

    public static Set<Role> getRolesFromAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(gr -> Role.valueOf(gr.getAuthority()))
                .collect(Collectors.toSet());
    }
}
