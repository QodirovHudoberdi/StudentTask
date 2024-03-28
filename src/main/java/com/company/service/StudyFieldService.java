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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public List<FieldStudiesEntity> getList(Integer pageNo, Integer pageSize) {
        Pageable page= PageRequest.of(pageNo,pageSize, Sort.by("id"));
        Page<FieldStudiesEntity> page1=studyFieldRepository.findAll(page);

      //  log.info("Request Get List  {}  of field studies", pageSize);
        if (page1.hasContent()) {
            return page1.getContent();
        }else {
            Pageable page11= PageRequest.of(0,5, Sort.by("id"));
            Page<FieldStudiesEntity> pageDefault=studyFieldRepository.findAll(page11);
            return pageDefault.getContent();
        }
    }

    public static FieldStudiesEntity toFieldStudiesEntity(FieldStudiesCreateDTO fieldStudiesCreateDTO) {
        return modelMapper.map(fieldStudiesCreateDTO, FieldStudiesEntity.class);
    }

    public static FieldStudiesDto toFieldStudiesDto(FieldStudiesEntity fieldStudiesEntity) {
        return modelMapper.map(fieldStudiesEntity, FieldStudiesDto.class);
    }
}