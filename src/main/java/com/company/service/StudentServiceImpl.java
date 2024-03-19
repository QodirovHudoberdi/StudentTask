package com.company.service;

import com.company.dto.StudentDTO;

import java.util.List;

public interface StudentServiceImpl {
    void updateStudent(Integer id, StudentDTO studentDTO);
    String delete(Integer id);
    StudentDTO getStudentById(Integer id);
    StudentDTO create(StudentDTO studentDto);
    List<StudentDTO> getList();
}
