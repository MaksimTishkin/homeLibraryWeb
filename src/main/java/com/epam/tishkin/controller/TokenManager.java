package com.epam.tishkin.controller;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.security.Key;

public class TokenManager {
    private final static Key key;

    static {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public void createToken(HttpServletResponse response, String login) {
        String jws = Jwts.builder()
                .setSubject(login)
                .signWith(key)
                .compact();
        Cookie cookie = new Cookie("token", jws);
        response.addCookie(cookie);
    }

    public boolean verifyToken(HttpServletRequest request) {
        try {
        Cookie cookie = getCookie(request);
        if (cookie != null) {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(cookie.getValue());
        } else {
            return false;
        }
    } catch (JwtException e) {
        return false;
    }
        return true;
}

    public Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cookieName = "token";
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie currentCookie : cookies) {
                if (cookieName.equals(currentCookie.getName())) {
                    cookie = currentCookie;
                    break;
                }
            }
        }
        return cookie;
    }
}
