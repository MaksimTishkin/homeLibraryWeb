package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import com.epam.tishkin.models.User;
import jakarta.servlet.http.HttpServletRequest;

public class AddBookmarkAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String title = request.getParameter("title");
        String inputPageNumber = request.getParameter("page");
        int pageNumber = isCorrectPageNumber(inputPageNumber);
        if (pageNumber < 0) {
            request.setAttribute(incorrectAttr, "Incorrect number of pages");
            return ConfigurationManager.getProperty("visitorPage");
        }
        User user = (User) request.getSession().getAttribute("user");
        if (userDAO.addBookmark(title, pageNumber, user)) {
            String completeAction = "Bookmark in the book " + title
                    + " has been added to page " + pageNumber;
            request.setAttribute(resultAttr, completeAction);
            HistoryWriter.write(completeAction);
        } else {
            request.setAttribute(incorrectAttr, "Incorrect book data or the bookmark " +
                    "in this book is already there");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }

    private int isCorrectPageNumber(String inputPage) {
        int incorrectFlag = -1;
        int page;
        try {
            page = Integer.parseInt(inputPage);
        } catch (NumberFormatException e) {
            return incorrectFlag;
        }
        if (page <= 0) {
            return incorrectFlag;
        }
        return page;
    }
}
