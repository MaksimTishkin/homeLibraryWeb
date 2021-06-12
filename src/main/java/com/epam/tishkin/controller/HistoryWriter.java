package com.epam.tishkin.controller;

import com.epam.tishkin.models.User;

import java.io.FileWriter;
import java.io.IOException;

public class HistoryWriter {
    private static User user;

    public static void setUser(User user) {
        HistoryWriter.user = user;
    }

    public static void write(String message) {
        try (FileWriter fileWriter = new FileWriter("history.txt", true)) {
            fileWriter.write(user.getLogin() + "- " + message + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
