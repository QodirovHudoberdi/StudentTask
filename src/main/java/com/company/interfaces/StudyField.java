package com.company.interfaces;

import com.company.dto.fieldstudy.FieldStudiesCreateDTO;
import com.company.dto.fieldstudy.FieldStudiesDto;

import java.util.List;

public interface StudyField {
    FieldStudiesDto create(FieldStudiesCreateDTO fieldStudiesCreateDTO);

    List<FieldStudiesDto> getList();
}