package com.company.service;

import com.company.dto.FieldStudiesDTO;
import com.company.entity.FieldStudiesEntity;
import com.company.repository.StudyFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyFieldService {

    @Autowired
    private StudyFieldRepository studyFieldRepository;

    public FieldStudiesDTO create(FieldStudiesDTO fieldstudiesDTO) {
        FieldStudiesEntity entity = new FieldStudiesEntity();
        entity.setName(fieldstudiesDTO.getName());
        entity.setUniversity_id(fieldstudiesDTO.getUniversityId());
        studyFieldRepository.save(entity);
        fieldstudiesDTO.setId(entity.getId());


        return fieldstudiesDTO;
    }
}
