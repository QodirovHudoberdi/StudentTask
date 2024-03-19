package com.company.service;

import com.company.dto.StudentDTO;
import com.company.entity.StudentEntity;
import com.company.exps.NotFoundException;
import com.company.exps.OkExceptions;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService implements StudentServiceImpl {


    @Autowired
    private StudentRepository studentRepository;

// Get Students list
    @Override
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
           // System.out.println(dto.toString());
        });
        return dtoList;
    }
    // Create Student
    @Override
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
    // Get one Student
    @Override
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
    // Delete Student
    @Override
    public String delete(Integer id) {
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException("Student Not Found");
        }
        studentRepository.delete(byId.get());
        return "Student deleted Successfully";
    }
    // Edit Student's data
    @Override
    public void updateStudent(Integer id, StudentDTO studentDTO) {
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException("That student is not Found");
        }
        StudentEntity studentEntity = byId.get();
        studentEntity.setFirst_name(orElse(studentDTO.getFirstName(), byId.get().getFirst_name()));
        studentEntity.setSurname(orElse(studentDTO.getSurName(), byId.get().getSurname()));
        studentEntity.setMiddle_name(orElse(studentDTO.getMiddleName(), byId.get().getMiddle_name()));
        studentEntity.setDescription(orElse(studentDTO.getDescription(), byId.get().getDescription()));
        studentEntity.setBirthDate(elseNull(studentDTO.getBirthdate(),byId.get().getBirthDate()));
        studentEntity.setStudyStartDate(elseNull(studentDTO.getStudyStartDate(),byId.get().getStudyStartDate()));
        studentEntity.setStudyEndDate(elseNull(studentDTO.getStudyEndDate(),byId.get().getStudyEndDate()));
        studentEntity.setStudyFieldId(
                studentDTO.getStudyFieldId()==null ? byId.get().getStudyFieldId():
                                                        studentEntity.getStudyFieldId());
        studentEntity.setGender(
                studentDTO.getGender()==null ? byId.get().getGender():
                                                        studentEntity.getGender());
        studentRepository.save(studentEntity);
        studentDTO.setId(studentEntity.getId());
        throw new OkExceptions("Student Updated");
    }


    public String orElse(String value ,String other) {
        return value != null ? value : other;
    }
    public LocalDate elseNull(String value ,LocalDate other) {
        return value != null ? LocalDate.parse(value) :other;
    }
}
