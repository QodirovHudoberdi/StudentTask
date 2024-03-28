package com.company.utils;

import com.company.exception.WrongException;
import com.company.service.StudentService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



public class ImageUtils {
    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());

    /**
     * Create image file and save to disc
     *
     * @param base64Image is  image's Base64 code
     * */
    public static void decoder(String base64Image, String pathFile,int maxsize) {
        try {


            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            if (imageByteArray.length>maxsize) {
                throw new WrongException("Image size is too big");
            }

            Files.write(Path.of(pathFile), imageByteArray, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Exception occurred while decoding and saving the image: " + e);
        }
    }

    public static void setUpLogger(){
        LOGGER.setLevel(Level.ALL);
    LOGGER.info("doing stuff");


    try{

        FileHandler fhandler = new FileHandler("Logfile.txt");
        SimpleFormatter sformatter = new SimpleFormatter();
        fhandler.setFormatter(sformatter);
        //This message does not log in the Logfile.txt, rather it logs in the console
        LOGGER.log(Level.WARNING, "first log");
        LOGGER.fine("information");
        LOGGER.addHandler(fhandler);

    }catch(  IOException | SecurityException e){
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    LOGGER.log(Level.WARNING, "Warning message");
    LOGGER.fine("This message is logged in the file");
}
}