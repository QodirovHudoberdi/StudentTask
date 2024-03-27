package com.company.service;

import com.company.dto.fieldstudy.FieldStudiesCreateDTO;
import com.company.dto.fieldstudy.FieldStudiesDto;
import com.company.entity.FieldStudiesEntity;
import com.company.entity.UniversityEntity;
import com.company.exception.WrongException;
import com.company.interfaces.StudyField;
import com.company.repository.StudyFieldRepository;
import com.company.repository.UniversityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudyFieldService implements StudyField {
    @Autowired
    private StudyFieldRepository studyFieldRepository;
    @Autowired
    private UniversityRepository universityRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    /**
     * Create Field Study
     *
     * @param fieldStudiesCreateDto is value's of new fieldStudios
     */
    @Override
    public FieldStudiesDto create(FieldStudiesCreateDTO fieldStudiesCreateDto) {
        Optional<UniversityEntity> universityEntity = universityRepository.findById(fieldStudiesCreateDto.getUniversityId());
        if (universityEntity.isEmpty()) {
            throw new WrongException("University Not Found");
        }

        FieldStudiesEntity entity = toFieldStudiesEntity(fieldStudiesCreateDto);
        studyFieldRepository.save(entity);
        FieldStudiesDto fieldStudiesDto = toFieldStudiesDto(entity);
        fieldStudiesDto.getUniversity().setName(universityEntity.get().getName());
        return fieldStudiesDto;
    }

    /**
     * Get list of  field of studies
     */

    @Override
    public List<FieldStudiesDto> getList() {
        Iterable<FieldStudiesEntity> all = studyFieldRepository.findAll();
        List<FieldStudiesDto> dtolist = new LinkedList<>();
        all.forEach(listEntity -> {
            FieldStudiesDto dto = toFieldStudiesDto(listEntity);
            dtolist.add(dto);
        });
        return dtolist;
    }

    public static FieldStudiesEntity toFieldStudiesEntity(FieldStudiesCreateDTO fieldStudiesCreateDTO) {
        return modelMapper.map(fieldStudiesCreateDTO, FieldStudiesEntity.class);
    }

    public static FieldStudiesDto toFieldStudiesDto(FieldStudiesEntity fieldStudiesEntity) {
        return modelMapper.map(fieldStudiesEntity, FieldStudiesDto.class);
    }
}