package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import com.epam.tishkin.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchBookByYearPagesNumberAndTitle implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        String title = request.getParameter("title");
        if (title.isEmpty()) {
            request.setAttribute(incorrectAttr, "Incorrect book title");
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
        List<Book> foundBooks = libraryDAO.searchBookByYearPagesNumberAndTitle(year, pages, title);
        if (foundBooks.isEmpty()) {
            request.setAttribute(incorrectAttr, "No books found");
        } else {
            request.setAttribute(resultAttr, foundBooks);
        }
        return ConfigurationManager.getProperty("visitorPage");
    }

    private int isCorrectYear(HttpServletRequest request) {
        int incorrectFlag = -1;
        int year;
        String inputYear = request.getParameter("year");
        try {
            year = Integer.parseInt(inputYear);
        } catch (NumberFormatException e) {
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
        return pages;
    }
}
