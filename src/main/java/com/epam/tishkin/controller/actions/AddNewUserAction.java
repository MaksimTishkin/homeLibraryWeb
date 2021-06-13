package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class AddNewUserAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login.isEmpty() || password.isEmpty()) {
            request.setAttribute(incorrectAttr, "Invalid value login/password");
            return ConfigurationManager.getProperty("visitorPage");
        }
        if (userDAO.addUser(login, password)) {
            String completeAction = "New user added - " + login;
            request.setAttribute(resultAttr, completeAction);
            HistoryWriter.write(completeAction);
        } else {
            request.setAttribute(incorrectAttr, "This user already exists - " + login);
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
