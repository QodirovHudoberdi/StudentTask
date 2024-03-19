package com.company.service;

import com.company.dto.FieldStudiesDTO;
import com.company.dto.StudentDTO;
import com.company.dto.UniversityDTO;

public interface EditServiceImpl {
    void updateUniversityName(UniversityDTO universityDTO, Integer id);
    void updateFieldName(Integer id, FieldStudiesDTO dto);
}
