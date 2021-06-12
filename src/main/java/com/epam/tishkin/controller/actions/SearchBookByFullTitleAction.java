package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import com.epam.tishkin.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchBookByFullTitleAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String title = request.getParameter("title");
        List<Book> foundBooks = libraryDAO.findBookByFullTitle(title);
        if (foundBooks.isEmpty()) {
            request.setAttribute("actionResult", "No books found");
        } else {
            request.setAttribute("actionResult", foundBooks);
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
