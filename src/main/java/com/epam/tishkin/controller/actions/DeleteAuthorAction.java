package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteAuthorAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String authorName = request.getParameter("name");
        if (authorName.isEmpty()) {
            request.setAttribute("incorrectInputData", "Incorrect author's name");
            return ConfigurationManager.getProperty("visitorPage");
        }
        if (libraryDAO.deleteAuthor(authorName)) {
            request.setAttribute("actionResult", authorName + " : author deleted");
        } else {
            request.setAttribute("actionResult", authorName + " : author not found");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
