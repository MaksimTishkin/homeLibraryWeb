package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddAuthorAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String authorName = request.getParameter("author");
        if (authorName.isEmpty()) {
            request.setAttribute(incorrectAttr, "Incorrect author's name");
            return ConfigurationManager.getProperty("visitorPage");
        }
        if (libraryDAO.addAuthor(authorName)) {
            String completeAction = authorName + " : author added";
            request.setAttribute(resultAttr, completeAction);
            HistoryWriter.write(request, completeAction);
        } else {
            request.setAttribute(resultAttr, authorName +
                    " : this author is already in the database");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
