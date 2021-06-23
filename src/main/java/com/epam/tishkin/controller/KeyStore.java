package com.epam.tishkin.controller;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class KeyStore {
    private final static Key key;

    static {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public static Key getKey() {
        return key;
    }
}
