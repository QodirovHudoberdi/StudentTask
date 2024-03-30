package com.company.service;

import com.company.config.ComponentContainer;
import com.company.dto.UniversityDTO;
import com.company.dto.fieldstudy.FieldStudiesDto;
import com.company.entity.FieldStudiesEntity;
import com.company.entity.UniversityEntity;
import com.company.exception.NotFoundException;
import com.company.exception.OkResponse;
import com.company.exception.WrongException;
import com.company.interfaces.Update;
import com.company.repository.StudyFieldRepository;
import com.company.repository.UniversityRepository;
import com.company.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UpdateService implements Update {
    LoggerUtil loggerUtil = ComponentContainer.loggerUtil;

    private final UniversityRepository universityRepository;

    private final StudyFieldRepository studyFieldRepository;

    /**
     * Update university Name
     *
     * @param universityDTO is value's University
     */
    @Override
    public void updateUniversityName(UniversityDTO universityDTO, Integer id) {
        loggerUtil.logInfo("Update University details ");
        Optional<UniversityEntity> byId = universityRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException("University  Not Found ");
        }
        if (byId.get().getName().equals(universityDTO.getName())) {
            throw new WrongException("It is Old University Name");
        }
        byId.get().setName(universityDTO.getName());
        universityRepository.save(byId.get());
        throw new OkResponse("University Name have Updated");
    }

    /**
     * Update Field Study Name
     *
     * @param dto is value's fieldStudios
     */
    @Override
    public void updateFieldName(Integer id, FieldStudiesDto dto) {
        Optional<FieldStudiesEntity> byId = studyFieldRepository.findById(id);
        if (byId.isEmpty()) {
            loggerUtil.logWarning("Field Of Study  Not Found ");
            throw new NotFoundException("Field Of Study  Not Found ");
        }
        if (byId.get().getName().equals(dto.getName())) {
            loggerUtil.logWarning("It is Old Field Name of Study");
            throw new WrongException("It is Old Field Name of Study");
        }
        byId.get().setName(dto.getName());
        studyFieldRepository.save(byId.get());
        loggerUtil.logInfo("Update Field of study details ");
        throw new OkResponse("Field Name was updated ");
    }
}