package com.company.service;

import com.company.dto.StudentDTO;
import com.company.entity.StudentEntity;
import com.company.exps.NotFoundException;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;


    public List<StudentDTO> getList() {

        Iterable<StudentEntity> all = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();

        all.forEach(listEntity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(listEntity.getId());
            dto.setFirstName(listEntity.getFirst_name());
            dto.setMiddleName(listEntity.getMiddle_name());
            dto.setSurName(listEntity.getSurname());
            dto.setDescription(listEntity.getDescription());
            dto.setBirthdate(String.valueOf(listEntity.getBirthDate()));
            dto.setCreatedTime(listEntity.getCreatedDate());
            dto.setGender(listEntity.getGender());
            dto.setStudyFieldId(listEntity.getStudyFieldId());
            dto.setStudyStartDate(String.valueOf(listEntity.getStudyStartDate()));
            dto.setStudyEndDate(String.valueOf(listEntity.getStudyEndDate()));
            dtoList.add(dto);
            /*System.out.println(dto.toString());*/
        });

        return dtoList;
    }

    public StudentDTO create(StudentDTO studentDto) {


        StudentEntity student = new StudentEntity();
        student.setId(studentDto.getId());
        student.setFirst_name(studentDto.getFirstName());
        student.setSurname(studentDto.getSurName());
        student.setMiddle_name(studentDto.getMiddleName());
        student.setDescription(studentDto.getDescription());
        student.setGender(studentDto.getGender());
        student.setStudyStartDate(LocalDate.parse((studentDto.getStudyStartDate())));
        student.setStudyFieldId(studentDto.getStudyFieldId());
        student.setBirthDate(LocalDate.parse(studentDto.getBirthdate()));


        student.setStudyEndDate(LocalDate.parse(studentDto.getStudyStartDate()).plusYears(2));

        studentRepository.save(student);
        studentDto.setId(student.getId());
        return studentDto;
    }

    public StudentDTO getStudentById(Integer id) {
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException("User Not Found ");
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(byId.get().getId());
        studentDTO.setBirthdate(String.valueOf(byId.get().getBirthDate()));
        studentDTO.setFirstName(byId.get().getFirst_name());
        studentDTO.setMiddleName(byId.get().getMiddle_name());
        studentDTO.setSurName(byId.get().getSurname());
        studentDTO.setDescription(byId.get().getDescription());
        studentDTO.setGender(byId.get().getGender());
        studentDTO.setStudyStartDate(String.valueOf(byId.get().getStudyStartDate()));
        studentDTO.setStudyEndDate(String.valueOf(byId.get().getStudyEndDate()));
        studentDTO.setCreatedTime(byId.get().getCreatedDate());
        studentDTO.setStudyFieldId(byId.get().getStudyFieldId());
        return studentDTO;


    }
}
