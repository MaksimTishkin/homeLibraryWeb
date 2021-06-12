package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class BlockUserAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        if (userDAO.blockUser(login)) {
            request.setAttribute("actionResult", "User deleted - " + login);
        } else {
            request.setAttribute("actionResult", "User does not exist - " + login);
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
