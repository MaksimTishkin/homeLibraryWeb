package com.epam.tishkin.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieService {
    TokenManager tokenManager = new TokenManager();

    public void addTokenToCookie(HttpServletResponse response, String login) {
        String jws = tokenManager.createToken(login);
        Cookie cookie = new Cookie("token", jws);
        response.addCookie(cookie);
    }

    public boolean verifyTokenFromCookie(HttpServletRequest request) {
        Cookie cookie = getCookie(request);
        if (cookie != null) {
            return tokenManager.verifyToken(cookie.getValue());
        }
        return false;
    }

    public void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request);
        if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    private Cookie getCookie(HttpServletRequest request) {
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
