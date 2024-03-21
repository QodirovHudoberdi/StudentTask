package com.company.interfaces;

import com.company.models.PhotoDTO;
import com.company.models.StudentDTO;

import java.util.List;

public interface Document {
    void getPdf(StudentDTO dto, PhotoDTO photo);

    void getExcel(List<StudentDTO> dto);
}