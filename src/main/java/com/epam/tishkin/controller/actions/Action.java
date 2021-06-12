package com.epam.tishkin.controller.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface Action {
    String execute(HttpServletRequest request) throws ServletException, IOException, ClassNotFoundException;
}
