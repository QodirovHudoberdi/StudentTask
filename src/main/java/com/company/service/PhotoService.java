package com.company.service;

import com.company.interfaces.Photo;
import com.company.models.PhotoDTO;
import com.company.models.StudentDTO;
import com.company.utils.ImageUtils;
import org.springframework.stereotype.Service;

@Service
public class PhotoService implements Photo {

/**
 * Save image of student
 *
 * @param dto Json of student fields
 * @param photo Base64b photo  of student
 * */
    @Override
    public String imageSaver(StudentDTO dto, PhotoDTO photo) {
        String fileName = "C:\\Users\\hudoberdi23\\IdeaProjects\\task\\src\\main\\resources\\images\\" + dto.getSurName() + dto.getFirstName() + ".png";
        ImageUtils.decoder(photo.getPhoto(), fileName);
        return fileName;
    }
}