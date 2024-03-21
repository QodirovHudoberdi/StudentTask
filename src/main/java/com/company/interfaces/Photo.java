package com.company.interfaces;

import com.company.models.PhotoDTO;
import com.company.models.StudentDTO;

public interface Photo {
    String imageSaver(StudentDTO dto, PhotoDTO photo);
}