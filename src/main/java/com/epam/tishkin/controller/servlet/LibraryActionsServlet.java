package com.epam.tishkin.controller.servlet;

import com.epam.tishkin.controller.CookieService;
import com.epam.tishkin.controller.TokenManager;
import com.epam.tishkin.controller.actions.Action;
import com.epam.tishkin.controller.actions.ActionFactory;
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
    private final CookieService cookieService = new CookieService();
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
            if (cookieService.verifyTokenFromCookie(request)) {
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
}
