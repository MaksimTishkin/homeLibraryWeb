package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public class ShowHistoryAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String historyAttr = ConfigurationManager.getProperty("historyAttr");
        List<String> fullHistory = userDAO.showHistory();
        request.setAttribute(historyAttr, fullHistory);
        return ConfigurationManager.getProperty("historyPage");
    }
}
