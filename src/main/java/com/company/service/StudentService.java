package com.company.service;

import com.company.dto.StudentDTO;
import com.company.entity.StudentEntity;
import com.company.exps.AlreadyExistException;
import com.company.exps.NotFoundException;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            dto.setSurName(listEntity.getSurname());
            dto.setDescription(listEntity.getDescription());
            dto.setBirthdate(listEntity.getBirthDate());
            dto.setCreatedTime(listEntity.getCreatedDate());
            dto.setGender(listEntity.getGender().toString());
            dto.setStudyFieldId(listEntity.getStudyFieldId());
            dto.setStudyStartDate(listEntity.getStudyStartDate());
            dto.setStudyEndDate(listEntity.getStudyEndDate());
            dtoList.add(dto);
            /*System.out.println(dto.toString());*/
        });

        return dtoList;
    }

    public StudentDTO create(StudentDTO studentDto) {


        StudentEntity student = new StudentEntity();
        student.setFirst_name(studentDto.getFirstName());
        student.setSurname(studentDto.getSurName());
        student.setMiddle_name(studentDto.getMiddleName());
        student.setDescription(studentDto.getDescription());
        student.setCreatedDate(studentDto.getCreatedTime());
        student.setStudyStartDate(studentDto.getStudyStartDate());
        student.setStudyEndDate(studentDto.getStudyEndDate());
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
        studentDTO.setBirthdate(byId.get().getBirthDate());
        studentDTO.setFirstName(byId.get().getFirst_name());
        studentDTO.setMiddleName(byId.get().getMiddle_name());
        studentDTO.setSurName(byId.get().getSurname());
        studentDTO.setDescription(byId.get().getDescription());
        studentDTO.setGender(String.valueOf(byId.get().getGender()));
        studentDTO.setStudyStartDate(byId.get().getStudyStartDate());
        studentDTO.setStudyEndDate(byId.get().getStudyEndDate());
        studentDTO.setCreatedTime(byId.get().getCreatedDate());
        studentDTO.setStudyFieldId(byId.get().getStudyFieldId());
        return studentDTO;


    }
}
