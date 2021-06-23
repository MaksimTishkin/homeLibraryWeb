package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import com.epam.tishkin.models.Book;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Year;

public class AddBookAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String ISBNumber = request.getParameter("ISBNumber");
        int year = Integer.parseInt(request.getParameter("year"));
        int pages = Integer.parseInt(request.getParameter("pages"));
        Book book = new Book(title, ISBNumber, year, pages);
        if (libraryDAO.addBook(book, author)) {
            String completeAction = book.getTitle() + " : book added";
            request.setAttribute(resultAttr, completeAction);
            HistoryWriter.write(request, completeAction);
        } else {
            request.setAttribute(resultAttr, book.getTitle() +
                    " : this book is already in the database");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }
}
