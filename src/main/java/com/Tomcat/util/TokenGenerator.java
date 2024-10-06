package com.Tomcat.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Base64;

public class TokenGenerator {

    public static String generateVerifyToken() {
        return RandomStringUtils.random(6, true, true);
    }

    public static String passwordEncoder(String password) {
        return Base64.getUrlEncoder().encodeToString(password.getBytes());
    }

}
