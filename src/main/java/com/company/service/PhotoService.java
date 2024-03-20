package com.company.service;

import com.company.dto.PhotoDTO;
import com.company.dto.StudentDTO;
import com.company.utils.ImageUtils;
import org.springframework.stereotype.Service;

@Service
public class PhotoService implements PhotoServiceImpl {

    // Save Image
    @Override
    public String imageSaver(StudentDTO dto, PhotoDTO photo) {
        String fileName = "C:\\Users\\hudoberdi23\\IdeaProjects\\task\\src\\main\\resources\\images\\" + dto.getSurName() + dto.getFirstName() + ".png";
        ImageUtils.decoder(photo.getPhoto(), fileName);
        return fileName;
    }


}
