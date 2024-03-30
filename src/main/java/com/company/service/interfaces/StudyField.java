package com.company.service.interfaces;

import com.company.aggregation.dto.fieldstudy.FieldStudiesRequestDTO;
import com.company.aggregation.dto.fieldstudy.FieldStudiesDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudyField {
    FieldStudiesDto create(FieldStudiesRequestDTO fieldStudiesRequestDTO, HttpServletRequest httpServletRequest);

    List<FieldStudiesDto> getList(Integer no, Integer size, HttpServletRequest httpServletRequest);
}