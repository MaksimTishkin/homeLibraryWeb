package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import com.epam.tishkin.models.Bookmark;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowBookmarksAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String login = (String) request.getSession().getAttribute("login");
        List<Bookmark> foundBookmarks = userDAO.showBooksWithBookmarks(login);
        if (foundBookmarks.isEmpty()) {
            request.setAttribute(incorrectAttr, "There is no bookmark");
        } else {
            request.setAttribute(resultAttr, foundBookmarks);
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
