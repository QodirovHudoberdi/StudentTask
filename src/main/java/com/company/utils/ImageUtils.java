package com.company.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.io.IOException;

public class ImageUtils {
    public static void decoder(String base64Image, String pathFile) {
        try {
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            Files.write(Path.of(pathFile), imageByteArray, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Exception occurred while decoding and saving the image: " + e);
        }
    }
}