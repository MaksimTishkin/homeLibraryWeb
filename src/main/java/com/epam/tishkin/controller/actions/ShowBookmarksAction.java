package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import com.epam.tishkin.models.Bookmark;
import com.epam.tishkin.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowBookmarksAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Bookmark> foundBookmarks = userDAO.showBooksWithBookmarks(user);
        if (foundBookmarks.isEmpty()) {
            request.setAttribute("actionResult", "There is no bookmark");
        } else {
            request.setAttribute("actionResult", foundBookmarks);
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
