package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import com.epam.tishkin.models.User;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteBookmarkAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String title = request.getParameter("title");
        User user = (User) request.getSession().getAttribute("user");
        if (userDAO.deleteBookmark(title, user)) {
            request.setAttribute("actionResult", "Bookmark in the book " + title
                    + " has been removed");
        } else {
            request.setAttribute("actionResult", "There is no bookmark in such a book");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
