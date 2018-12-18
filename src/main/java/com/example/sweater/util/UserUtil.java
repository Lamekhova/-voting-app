package com.example.sweater.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserUtil {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    public static boolean isEncodePassword(String password, String encodePassword) {
        return passwordEncoder.matches(password, encodePassword);
    }
}
