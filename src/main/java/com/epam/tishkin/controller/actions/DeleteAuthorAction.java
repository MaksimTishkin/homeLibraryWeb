package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteAuthorAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String authorName = request.getParameter("name");
        if (authorName.isEmpty()) {
            request.setAttribute(incorrectAttr, "Incorrect author's name");
            return ConfigurationManager.getProperty("visitorPage");
        }
        if (libraryDAO.deleteAuthor(authorName)) {
            String completeAction = authorName + " : author deleted";
            request.setAttribute(resultAttr, completeAction);
            HistoryWriter.write(request, completeAction);
        } else {
            request.setAttribute(incorrectAttr, authorName + " : author not found");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
