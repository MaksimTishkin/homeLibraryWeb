package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import com.epam.tishkin.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchBookForTitleAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String title = request.getParameter("title");
        List<Book> findBooks = libraryDAO.searchBookForTitle(title);
        if (findBooks.isEmpty()) {
            request.setAttribute(incorrectAttr, "No books found");
        } else {
            request.setAttribute(resultAttr, findBooks);
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
