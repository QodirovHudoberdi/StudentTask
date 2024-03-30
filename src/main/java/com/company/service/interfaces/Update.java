package com.company.service.interfaces;

import com.company.aggregation.dto.UniversityDTO;
import com.company.aggregation.dto.fieldstudy.FieldStudiesDto;

import javax.servlet.http.HttpServletRequest;

public interface Update {
    void updateUniversityName(UniversityDTO universityDTO, Integer id, HttpServletRequest httpServletRequest);

    void updateFieldName(Integer id, FieldStudiesDto dto, HttpServletRequest httpServletRequest);
}