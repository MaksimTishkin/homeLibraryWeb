package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class AddAuthorAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String authorName = request.getParameter("name");
        if (authorName.isEmpty()) {
            request.setAttribute("incorrectInputData", "Incorrect author's name");
            return ConfigurationManager.getProperty("visitorPage");
        }
        if (libraryDAO.addAuthor(authorName)) {
            String completeAction = authorName + " : author added";
            request.setAttribute("actionResult", completeAction);
            HistoryWriter.write(completeAction);
        } else {
            request.setAttribute("actionResult", authorName +
                    " : this author is already in the database");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
