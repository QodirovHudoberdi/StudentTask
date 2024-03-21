package com.company.service;

import com.company.dto.PhotoDTO;
import com.company.dto.StudentDTO;

public interface PhotoServiceImpl {
    String imageSaver(StudentDTO dto, PhotoDTO photo);
}
