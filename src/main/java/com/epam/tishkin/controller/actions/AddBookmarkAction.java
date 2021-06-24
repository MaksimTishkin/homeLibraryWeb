package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class AddBookmarkAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String title = request.getParameter("title");
        int pageNumber = Integer.parseInt(request.getParameter("pages"));
        String login = (String) request.getSession().getAttribute("login");
        if (userDAO.addBookmark(title, pageNumber, login)) {
            String completeAction = "Bookmark in the book " + title
                    + " has been added to page " + pageNumber;
            request.setAttribute(resultAttr, completeAction);
            HistoryWriter.write(request, completeAction);
        } else {
            request.setAttribute(incorrectAttr, "Incorrect book data or the bookmark " +
                    "in this book is already there");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
