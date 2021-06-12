package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.dao.impl.UserDatabaseDAO;
import com.epam.tishkin.models.User;
import jakarta.servlet.http.HttpServletRequest;

public class AddBookmarkAction implements Action {
    private final UserDAO userDAO = new UserDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String title = request.getParameter("title");
        String inputPageNumber = request.getParameter("page");
        int pageNumber = isCorrectPageNumber(inputPageNumber);
        if (pageNumber < 0) {
            request.setAttribute("incorrectInputData", "Incorrect number of pages");
            return ConfigurationManager.getProperty("visitorPage");
        }
        User user = (User) request.getSession().getAttribute("user");
        if (userDAO.addBookmark(title, pageNumber, user)) {
            request.setAttribute("actionResult", "Bookmark in the book " + title
                    + " has been added to page " + pageNumber);
        } else {
            request.setAttribute("actionResult", "Incorrect book data");
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
