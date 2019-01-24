package com.example.sweater.util;

import com.example.sweater.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserUtil {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static String checkEncodePassword(String rawPassword, String encodePassword) {
        String actualPassword = encodePassword;
        if (!passwordEncoder.matches(rawPassword, encodePassword)) {
            actualPassword = passwordEncoder.encode(rawPassword);
        }
        return actualPassword;
    }

    public static Set<Role> getRolesFromAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(gr -> Role.valueOf(gr.getAuthority()))
                .collect(Collectors.toSet());
    }
}
