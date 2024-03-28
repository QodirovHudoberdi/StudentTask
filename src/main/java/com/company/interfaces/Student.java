package com.company.interfaces;

import com.company.dto.PhotoDTO;
import com.company.dto.student.StudentCreateDTO;
import com.company.dto.student.StudentDto;
import com.company.entity.StudentEntity;

import java.util.List;

public interface Student {
    StudentDto updateStudent(Integer id, StudentCreateDTO studentCreateDTO);

    void delete(Integer id);

    StudentDto getStudentById(Integer id, PhotoDTO photo);

    StudentDto create(StudentCreateDTO studentCreateDto);

    List<StudentEntity> getList(Integer pageNo , Integer pageSize);

    void getList1();
}