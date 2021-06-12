package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import com.epam.tishkin.models.*;

import jakarta.servlet.http.HttpServletRequest;

public class LoginAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("name");
        String password = request.getParameter("password");
        User user = userDAO.userAuthorization(login, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            HistoryWriter.setUser(user);
            return ConfigurationManager.getProperty("visitorPage");
        }
        request.setAttribute("errorAuthorization", "Incorrect login/password");
        return ConfigurationManager.getProperty("loginPage");
    }
}
