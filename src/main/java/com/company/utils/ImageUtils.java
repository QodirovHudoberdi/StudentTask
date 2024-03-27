package com.company.utils;

import com.company.exception.WrongException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.io.IOException;


public class ImageUtils {

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
}