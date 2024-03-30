package com.company.service.interfaces;

import com.company.aggregation.dto.UniversityDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface University {
    UniversityDTO create(UniversityDTO universityDTO, HttpServletRequest httpServletRequest);

    List<UniversityDTO> getList(Integer no, Integer size, HttpServletRequest httpServletRequest);
}