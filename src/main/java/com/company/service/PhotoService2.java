package com.company.service;

import com.company.dto.PhotoDTO;
import com.company.dto.StudentDTO;
import com.company.utils.ImageUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class PhotoService2 {

    public void imageSaver(String path, StudentDTO dto) {
        try {
            BufferedImage image = ImageIO.read(new File(path));

            int width = 300;
            int height = 300;

            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(image, 0, 0, width, height, null);
            g2d.dispose();

            File output = new File("src/main/resources/images/" + dto.getSurName() + "Image.png");
            ImageIO.write(resizedImage, "png", output);

            System.out.println("Image saved successfully.");
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }
    }
public byte[] imageSaver(StudentDTO dto, PhotoDTO photo) {
    String fileName = "C:\\Users\\hudoberdi23\\IdeaProjects\\task\\src\\main\\resources\\images"+dto.getSurName()+dto.getFirstName()+".png";
    try {
       return ImageUtils.saveImageFromBase64(photo.getPhoto(), fileName);

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
}
