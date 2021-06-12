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
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String title = request.getParameter("title");
        if (title.isEmpty()) {
            request.setAttribute(incorrectAttr, "Incorrect book title");
            return ConfigurationManager.getProperty("visitorPage");
        }
        String author = request.getParameter("author");
        if (author.isEmpty()) {
            request.setAttribute(incorrectAttr, "Incorrect author of the book");
            return ConfigurationManager.getProperty("visitorPage");
        }
        String ISBNumber = request.getParameter("ISBNumber");
        if (!isCorrectNumber(ISBNumber)) {
            request.setAttribute(incorrectAttr, "Incorrect ISBN Number");
            return ConfigurationManager.getProperty("visitorPage");
        }
        int year = isCorrectYear(request);
        if (year < 0) {
            request.setAttribute(incorrectAttr, "Incorrect year of publication");
            return ConfigurationManager.getProperty("visitorPage");
        }
        int pages = isCorrectPagesNumber(request);
        if (pages < 0) {
            request.setAttribute(incorrectAttr, "Incorrect number of pages");
            return ConfigurationManager.getProperty("visitorPage");
        }
        Book book = new Book(title, ISBNumber, year, pages);
        if (libraryDAO.addBook(book, author)) {
            String completeAction = book.getTitle() + " : book added";
            request.setAttribute(resultAttr, completeAction);
            HistoryWriter.write(completeAction);
        } else {
            request.setAttribute(resultAttr, book.getTitle() +
                    " : this book is already in the database");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }

    private boolean isCorrectNumber(String ISBNumber) {
        if (ISBNumber.length() != 13) {
            return false;
        }
        try {
            Long.parseLong(ISBNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private int isCorrectYear(HttpServletRequest request) {
        int incorrectFlag = -1;
        int firstPrintedBookInWorld = 1564;
        int year;
        String inputYear = request.getParameter("year");
        try {
            year = Integer.parseInt(inputYear);
        } catch (NumberFormatException e) {
            return incorrectFlag;
        }
        if (year < firstPrintedBookInWorld || year > Year.now().getValue()) {
            return incorrectFlag;
        }
        return year;
    }

    private int isCorrectPagesNumber(HttpServletRequest request) {
        int incorrectFlag = -1;
        String inputPages = request.getParameter("pages");
        int pages;
        try {
            pages = Integer.parseInt(inputPages);
        } catch (NumberFormatException e) {
            return incorrectFlag;
        }
        if (pages <= 0) {
            return incorrectFlag;
        }
        return pages;
    }
}
