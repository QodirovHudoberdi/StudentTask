package com.company.interfaces;

import com.company.dto.PhotoDTO;
import com.company.dto.student.StudentDto;
import com.company.entity.StudentEntity;

import java.util.List;

public interface Documents {
    void getPdf(StudentDto dto, PhotoDTO photo);

    void getExcel(List<StudentEntity> dto);
}