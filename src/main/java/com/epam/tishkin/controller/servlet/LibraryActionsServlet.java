package com.epam.tishkin.controller.servlet;

import com.epam.tishkin.controller.KeyStore;
import com.epam.tishkin.controller.actions.Action;
import com.epam.tishkin.controller.actions.ActionFactory;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/controller")
public class LibraryActionsServlet extends HttpServlet {
    private final static Logger logger = LogManager.getLogger(LibraryActionsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        service(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        service(request, response);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (checkToken(request)) {
                Action action = ActionFactory.getAction(request);
                String view = action.execute(request);
                request.getRequestDispatcher(view).forward(request, response);
            } else {
                request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            }
        } catch (IOException | ClassNotFoundException | ServletException e) {
            logger.error(e.getMessage());
        }
    }

    private boolean checkToken(HttpServletRequest request) {
        try {
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
            if (cookie != null) {
                Jwts.parserBuilder()
                        .setSigningKey(KeyStore.getKey())
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
}
