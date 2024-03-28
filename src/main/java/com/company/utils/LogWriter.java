package com.company.utils;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogWriter {

    private static final String LOG_FOLDER_PATH = "src/main/resources/";
    private static final String LOG_FILE_NAME = "application.log";

    public  void log(Logger logger) {
        try {
            String logFilePath = LOG_FOLDER_PATH + LOG_FILE_NAME;
            FileHandler fileHandler = new FileHandler(logFilePath);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
            logger.info("This is an informational message.");
            fileHandler.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}