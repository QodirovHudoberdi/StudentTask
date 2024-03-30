package com.company.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

    public class LoggerUtil {
        private static final String LOG_FOLDER_PATH = "src/main/log/";
        private static final String LOG_FILE_NAME = "application.txt";

    private static Logger logger;

    public   void initializeLogger() {
        logger = Logger.getLogger("Logger5929");

        try {
            Path logFolderPath = Paths.get(LOG_FOLDER_PATH+LOG_FILE_NAME);
            if (!Files.exists(logFolderPath)) {
                Files.createFile(logFolderPath);
            }

            String logFilePath = LOG_FOLDER_PATH + LOG_FILE_NAME;
            FileHandler fileHandler = new FileHandler(logFilePath);
            fileHandler.setFormatter(new SimpleFormatter());


            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void logInfo(String message) {
        logger.info(message);
    }

    public  void logWarning(String message) {
        logger.warning(message);
    }

    public  void logError(String message) {
        logger.severe(message);
    }
}