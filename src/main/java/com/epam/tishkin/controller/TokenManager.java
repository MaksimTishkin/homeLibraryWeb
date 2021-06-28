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

    public String createToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .signWith(key)
                .compact();
    }

    public boolean verifyToken(String jws) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jws);
        } catch (JwtException e) {
        return false;
    }
        return true;
    }
}
