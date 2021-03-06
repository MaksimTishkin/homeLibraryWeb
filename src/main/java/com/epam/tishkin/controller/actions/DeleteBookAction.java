package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteBookAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        if (libraryDAO.deleteBook(title, author)) {
            String completeAction = title + " : book deleted";
            request.setAttribute(resultAttr, completeAction);
            HistoryWriter.write(request, completeAction);
        } else {
            request.setAttribute(incorrectAttr, title + ": book not found");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
