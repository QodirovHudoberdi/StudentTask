package com.company.interfaces;

import com.company.dto.PhotoDTO;
import com.company.dto.studen.StudentDto;

import java.util.List;

public interface Documents {
    void getPdf(StudentDto dto, PhotoDTO photo);

    void getExcel(List<StudentDto> dto);
}