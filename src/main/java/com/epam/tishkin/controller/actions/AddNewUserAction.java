package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class AddNewUserAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (userDAO.addUser(login, password)) {
            request.setAttribute("actionResult", "New user added - " + login);
        } else {
            request.setAttribute("actionResult", "This user already exists - " + login);
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
