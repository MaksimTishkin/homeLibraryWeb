package com.epam.tishkin.controller.servlet;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.controller.KeyStore;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import com.epam.tishkin.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.Key;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDatabaseDAO();
    private final static Logger logger = LogManager.getLogger(AuthorizationServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("command");
        String view;
        try {
            if (action.equals("login")) {
                view = login(request, response);
            } else {
                view = logout(request);
            }
            request.getRequestDispatcher(view).forward(request, response);
        } catch (IOException | ServletException e) {
            logger.error(e.getMessage());
        }
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        String errorAuthorizationAttr = ConfigurationManager.getProperty("errorAuthorizationAttr");
        String login = request.getParameter("name");
        String password = request.getParameter("password");
        User user = userDAO.userAuthorization(login, password);
        if (user != null) {
            Key key = KeyStore.getKey();
            String jws = Jwts.builder()
                    .setSubject(login)
                    .signWith(key)
                    .compact();
            Cookie cookie = new Cookie("token", jws);
            response.addCookie(cookie);
            request.getSession().setAttribute("login", user.getLogin());
            request.getSession().setAttribute("role", user.getRole());
            HistoryWriter.write(request, "is logged in");
            return ConfigurationManager.getProperty("visitorPage");
        }
        request.setAttribute(errorAuthorizationAttr, "Incorrect login/password");
        return ConfigurationManager.getProperty("loginPage");
    }

    private String logout(HttpServletRequest request) {
        HistoryWriter.write(request, "is logged out");
        request.getSession().invalidate();
        return ConfigurationManager.getProperty("indexPage");
    }
}
