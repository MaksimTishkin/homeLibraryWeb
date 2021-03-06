package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteBookmarkAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String title = request.getParameter("title");
        String login = (String) request.getSession().getAttribute("login");
        if (userDAO.deleteBookmark(title, login)) {
            String completeAction = "Bookmark in the book " + title + " has been removed";
            request.setAttribute(resultAttr, completeAction);
            HistoryWriter.write(request, completeAction);
        } else {
            request.setAttribute(incorrectAttr, "There is no bookmark in such a book");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
