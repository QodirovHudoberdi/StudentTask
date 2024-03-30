package com.company.service.interfaces;

import com.company.aggregation.dto.PhotoDTO;
import com.company.aggregation.dto.student.StudentDto;
import com.company.aggregation.entity.StudentEntity;

import java.util.List;

public interface Documents {
    void getPdf(StudentDto dto, PhotoDTO photo);

    void getExcel(List<StudentEntity> dto);
}