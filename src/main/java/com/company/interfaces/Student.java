package com.company.interfaces;

import com.company.dto.PhotoDTO;
import com.company.dto.studen.StudentCreateDTO;
import com.company.dto.studen.StudentDto;

import java.util.List;

public interface Student {
    void updateStudent(Integer id, StudentCreateDTO studentCreateDTO);

    void delete(Integer id);

    StudentDto getStudentById(Integer id, PhotoDTO photo);

    StudentDto create(StudentCreateDTO studentCreateDto);

    List<StudentDto> getList();
}