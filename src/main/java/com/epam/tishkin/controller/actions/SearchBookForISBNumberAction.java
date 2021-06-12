package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import com.epam.tishkin.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchBookForISBNumberAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String ISBNumber = request.getParameter("ISBNumber");
        List<Book> foundBooks = libraryDAO.searchBookForISBN(ISBNumber);
        if (foundBooks.isEmpty()) {
            request.setAttribute(incorrectAttr, "No books found");
        } else {
            request.setAttribute(resultAttr, foundBooks);
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
