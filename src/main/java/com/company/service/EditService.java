package com.company.service;

import com.company.dto.FieldStudiesDTO;
import com.company.dto.StudentDTO;
import com.company.dto.UniversityDTO;
import com.company.entity.FieldStudiesEntity;
import com.company.entity.StudentEntity;
import com.company.entity.UniversityEntity;
import com.company.exps.AlreadyExistException;
import com.company.exps.MistakeException;
import com.company.exps.NotFoundException;
import com.company.repository.EditRepository;
import com.company.repository.StudyFieldRepository;
import com.company.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EditService {

    @Autowired
    private EditRepository editRepository;
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private StudyFieldRepository studyFieldRepository;

    public void updateSTD(StudentDTO dto) {
        Optional<StudentEntity> byId = editRepository.findById(dto.getId());
        if (byId.isEmpty()) {
            throw new NotFoundException("User Not Found ");
        }
        if (byId.get().getStudyStartDate().equals(String.valueOf(dto.getStudyStartDate()))) {
            throw new AlreadyExistException("Study Start was not edit ");
        }
        byId.get().setStudyStartDate(LocalDate.parse(dto.getStudyStartDate()));
        byId.get().setStudyEndDate(LocalDate.parse(dto.getStudyStartDate()).plusYears(2));
        editRepository.save(byId.get());
    }

    public void updateSurname(String surname, Integer id) {
        Optional<StudentEntity> bySurname = editRepository.findById(id);
        if (bySurname.isEmpty()) {
            throw new NotFoundException("Surname was not match");
        }
        if (bySurname.get().getSurname().equals(surname)) {
            throw new MistakeException("It is Old Surname");
        }
        bySurname.get().setSurname(surname);
        editRepository.save(bySurname.get());
    }

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

    }

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

    }
}
