package com.epam.tishkin.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    final static Logger logger = LogManager.getLogger(ConfigurationManager.class);
    private final static Properties properties = new Properties();

    static {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("config.properties");
            properties.load(input);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
