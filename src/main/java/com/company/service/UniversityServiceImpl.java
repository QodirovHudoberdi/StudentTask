package com.company.service;

import com.company.dto.UniversityDTO;

import java.util.List;

public interface UniversityServiceImpl {
    UniversityDTO create(UniversityDTO universityDTO);
    List<UniversityDTO> getList();


}
