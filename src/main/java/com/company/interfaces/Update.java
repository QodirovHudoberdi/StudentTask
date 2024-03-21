package com.company.interfaces;

import com.company.models.FieldStudiesDTO;
import com.company.models.UniversityDTO;

public interface Update {
    void updateUniversityName(UniversityDTO universityDTO, Integer id);
    void updateFieldName(Integer id, FieldStudiesDTO dto);
}