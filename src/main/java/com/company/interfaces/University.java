package com.company.interfaces;

import com.company.dto.UniversityDTO;
import com.company.entity.UniversityEntity;
import com.company.utils.LoggerUtil;

import java.util.List;

public interface University {
    UniversityDTO create(UniversityDTO universityDTO);

    List<UniversityEntity> getList(Integer no, Integer size);
}