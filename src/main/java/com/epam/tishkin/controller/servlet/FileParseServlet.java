package com.epam.tishkin.controller.servlet;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.impl.LibraryDatabaseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

@WebServlet("/fileParse")
@MultipartConfig
public class FileParseServlet extends HttpServlet {
    private final LibraryDAO libraryDAO = new LibraryDatabaseDAO();
    private final static Logger logger = LogManager.getLogger(FileParseServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String incorrectAttr = ConfigurationManager.getProperty("incorrectDataAttr");
        String resultAttr = ConfigurationManager.getProperty("resultActionAttr");
        try {
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            if (fileName.endsWith("csv")) {
                int numberOfBooksAdded = addBooksFromCSV(filePart);
                String completeAction = "number of books added from CSV: " + numberOfBooksAdded;
                request.setAttribute(resultAttr, completeAction);
                HistoryWriter.write(request, completeAction);
            } else if (fileName.endsWith("json")) {
                int numberOfBooksAdded = addBooksFromJson(filePart);
                String completeAction = "number of books added from JSON: " + numberOfBooksAdded;
                request.setAttribute(resultAttr, completeAction);
                HistoryWriter.write(request, completeAction);
            }
            else {
                request.setAttribute(incorrectAttr, "Incorrect file format");
            }
            String page = ConfigurationManager.getProperty("visitorPage");
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private synchronized int addBooksFromCSV(Part filePart) {
        try (OutputStream outputStream = new FileOutputStream("catalog.csv", false)) {
            InputStream fileContent = filePart.getInputStream();
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return libraryDAO.addBooksFromCSV("catalog.csv");
    }

    private synchronized int addBooksFromJson(Part filePart) {
        try (OutputStream outputStream = new FileOutputStream("catalog.json", false)) {
            InputStream fileContent = filePart.getInputStream();
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return libraryDAO.addBooksFromJSON("catalog.json");
    }
}

