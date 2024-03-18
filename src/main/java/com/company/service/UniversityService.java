package com.company.service;

import com.company.dto.UniversityDTO;
import com.company.entity.UniversityEntity;
import com.company.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    public UniversityDTO create(UniversityDTO universityDTO) {
        UniversityEntity universityEntity = new UniversityEntity();
        universityEntity.setName(universityDTO.getName());
        universityRepository.save(universityEntity);
        universityDTO.setId(universityEntity.getId());
        return universityDTO;


    }
}
