package com.company.service;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

@Service
public class PhotoService {
    public void imageSaver(String path) {
        try {

            BufferedImage image = ImageIO.read(new File(path));


            File output = new File("src/main/resources/fifty.png");
            ImageIO.write(image, "png", output);

            System.out.println("Image saved successfully.");
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }
    }

}

