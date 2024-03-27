package com.company.interfaces;

import com.company.dto.UniversityDTO;

import java.util.List;

public interface University {
    UniversityDTO create(UniversityDTO universityDTO);

    List<UniversityDTO> getList();
}