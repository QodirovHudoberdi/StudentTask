package com.company.service;

import com.company.dto.FieldStudiesDTO;
import com.company.dto.UniversityDTO;
import com.company.entity.FieldStudiesEntity;
import com.company.entity.UniversityEntity;
import com.company.exps.MistakeException;
import com.company.exps.NotFoundException;
import com.company.exps.OkExceptions;
import com.company.repository.StudyFieldRepository;
import com.company.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditService implements EditServiceImpl {
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private StudyFieldRepository studyFieldRepository;

    //Update University Name
    @Override
    public void updateUniversityName(UniversityDTO universityDTO, Integer id) {
        Optional<UniversityEntity> byId = universityRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException("University  Not Found ");
        }
        if (byId.get().getName().equals(universityDTO.getName())) {
            throw new MistakeException("It is Old University Name");
        }
        byId.get().setName(universityDTO.getName());
        universityRepository.save(byId.get());
        throw new OkExceptions("University Name have Updated");

    }

    // Update Field Study Name
    @Override
    public void updateFieldName(Integer id, FieldStudiesDTO dto) {
        Optional<FieldStudiesEntity> byId = studyFieldRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException("Field Of Study  Not Found ");
        }
        if (byId.get().getName().equals(dto.getName())) {
            throw new MistakeException("It is Old Field Name of Study");
        }
        byId.get().setName(dto.getName());
        studyFieldRepository.save(byId.get());
        throw new OkExceptions("Field Name was updated ");
    }


}
