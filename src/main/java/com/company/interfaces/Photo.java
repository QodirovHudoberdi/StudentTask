package com.company.interfaces;

import com.company.dto.PhotoDTO;
import com.company.dto.studen.StudentDto;

public interface Photo {
    String imageSaver(StudentDto dto, PhotoDTO photo);
}