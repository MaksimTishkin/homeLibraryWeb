package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteBookAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        if (title.isEmpty()) {
            request.setAttribute("incorrectInputData", "Incorrect book title");
            return ConfigurationManager.getProperty("visitorPage");
        }
        if (author.isEmpty()) {
            request.setAttribute("incorrectInputData", "Incorrect author of the book");
            return ConfigurationManager.getProperty("visitorPage");
        }
        if (libraryDAO.deleteBook(title, author)) {
            request.setAttribute("actionResult", title + " : book deleted");
        } else {
            request.setAttribute("actionResult", title + ": book not found");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
