package com.company.interfaces;

import com.company.dto.fieldstudy.FieldStudiesRequestDTO;
import com.company.dto.fieldstudy.FieldStudiesDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudyField {
    FieldStudiesDto create(FieldStudiesRequestDTO fieldStudiesRequestDTO, HttpServletRequest httpServletRequest);

    List<FieldStudiesDto> getList(Integer no, Integer size, HttpServletRequest httpServletRequest);
}