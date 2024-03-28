package com.company.service;

import com.company.dto.PhotoDTO;
import com.company.dto.student.StudentDto;
import com.company.interfaces.Photo;
import com.company.utils.ImageUtils;
import org.springframework.stereotype.Service;

@Service
public class PhotoService implements Photo {

    /**
     * Save image of student
     *
     * @param dto   Json of student fields
     * @param photo Base64b photo  of student
     */
    @Override
    public String imageSaver(StudentDto dto, PhotoDTO photo) {
        String fileName = "C:\\Users\\hudoberdi23\\IdeaProjects\\task\\src\\main\\resources\\images\\" + dto.getSurName() + dto.getFirstName() + ".png";
        int maxSize = 1024 * 1024;
        ImageUtils.decoder(photo.getPhoto(), fileName, maxSize);
        return fileName;
    }
}