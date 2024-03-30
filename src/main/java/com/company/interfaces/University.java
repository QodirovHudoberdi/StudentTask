package com.company.interfaces;

import com.company.dto.UniversityDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface University {
    UniversityDTO create(UniversityDTO universityDTO, HttpServletRequest httpServletRequest);

    List<UniversityDTO> getList(Integer no, Integer size, HttpServletRequest httpServletRequest);
}