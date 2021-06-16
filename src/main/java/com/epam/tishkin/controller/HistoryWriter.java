package com.epam.tishkin.controller;

import com.epam.tishkin.models.User;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class HistoryWriter {
    private final static Logger logger = LogManager.getLogger(HistoryWriter.class);

    public static synchronized void write(HttpServletRequest request, String message) {
        try (FileWriter fileWriter = new FileWriter("history.txt", true)) {
            User user = (User) request.getSession().getAttribute("user");
            fileWriter.write(user.getLogin() + "- " + message + "\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
