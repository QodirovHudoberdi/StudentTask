package com.company.service;

import com.company.dto.FieldStudiesDTO;
import com.company.entity.FieldStudiesEntity;
import com.company.repository.StudyFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudyFieldService implements StudyFieldServiceImpl {
    @Autowired
    private StudyFieldRepository studyFieldRepository;

    // Create Field Study
    @Override
    public FieldStudiesDTO create(FieldStudiesDTO fieldstudiesDTO) {
        FieldStudiesEntity entity = new FieldStudiesEntity();
        entity.setName(fieldstudiesDTO.getName());
        entity.setUniversity_id(fieldstudiesDTO.getUniversityId());
        studyFieldRepository.save(entity);
        fieldstudiesDTO.setId(entity.getId());
        return fieldstudiesDTO;
    }

    // Get Fields of Study Lists
    @Override
    public List<FieldStudiesDTO> getList1() {
        Iterable<FieldStudiesEntity> all = studyFieldRepository.findAll();
        List<FieldStudiesDTO> dtolist = new LinkedList<>();
        all.forEach(listEntity -> {
            FieldStudiesDTO dto = new FieldStudiesDTO();
            dto.setId(listEntity.getId());
            dto.setName(listEntity.getName());
            dto.setUniversityId(listEntity.getUniversity_id());
            dtolist.add(dto);
        });
        return dtolist;
    }
}