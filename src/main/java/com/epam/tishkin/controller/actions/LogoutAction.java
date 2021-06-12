package com.epam.tishkin.controller.actions;

import com.epam.tishkin.controller.ConfigurationManager;
import com.epam.tishkin.controller.HistoryWriter;
import com.epam.tishkin.dao.impl.HibernateUtil;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutAction implements Action {

    @Override
    public String execute(HttpServletRequest request) {
        HistoryWriter.write("is logged out");
        request.getSession().invalidate();
        return ConfigurationManager.getProperty("indexPage");
    }
}
