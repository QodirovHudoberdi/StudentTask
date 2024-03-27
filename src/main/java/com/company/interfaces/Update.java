package com.company.interfaces;

import com.company.dto.UniversityDTO;
import com.company.dto.fieldstudy.FieldStudiesDto;

public interface Update {
    void updateUniversityName(UniversityDTO universityDTO, Integer id);

    void updateFieldName(Integer id, FieldStudiesDto dto);
}