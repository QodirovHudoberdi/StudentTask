package com.company.service.interfaces;

import com.company.aggregation.dto.PhotoDTO;
import com.company.aggregation.dto.student.StudentDto;

public interface Photo {
    String imageSaver(StudentDto dto, PhotoDTO photo);
}