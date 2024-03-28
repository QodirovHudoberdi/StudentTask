package com.company.interfaces;

import com.company.dto.PhotoDTO;
import com.company.dto.student.StudentDto;

public interface Photo {
    String imageSaver(StudentDto dto, PhotoDTO photo);
}