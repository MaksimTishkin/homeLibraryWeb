package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import com.epam.tishkin.models.Book;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Year;
import java.util.List;

public class SearchBooksByYearRangeAction implements Action {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();

    public String execute(HttpServletRequest request) {
        String firstYear = request.getParameter("firstYear");
        String secondYear= request.getParameter("secondYear");
        int startYear = isCorrectYear(firstYear);
        int finishYear = isCorrectYear(secondYear);
        if (startYear > 0 && finishYear > 0 && startYear <= finishYear) {
            List<Book> foundBooks = libraryDAO.searchBooksByYearRange(startYear, finishYear);
            if (foundBooks.isEmpty()) {
                request.setAttribute("actionResult", "No books found");
            } else {
                request.setAttribute("actionResult", foundBooks);
            }
        } else {
            request.setAttribute("incorrectInputData", "Incorrect year value");
        }
        return ConfigurationManager.getProperty("visitorPage");
    }

    private int isCorrectYear(String inputYear) {
        int incorrectFlag = -1;
        int year;
        try {
            year = Integer.parseInt(inputYear);
        } catch (NumberFormatException e) {
            return incorrectFlag;
        }
        return year;
    }
}
