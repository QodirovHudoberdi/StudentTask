package com.company.interfaces;

import com.company.models.StudentDTO;

import java.util.List;

public interface Student {
    void updateStudent(Integer id, StudentDTO studentDTO);
    void delete(Integer id);
    StudentDTO getStudentById(Integer id);
    StudentDTO create(StudentDTO studentDto);
    List<StudentDTO> getList();
}