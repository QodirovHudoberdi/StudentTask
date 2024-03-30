package com.company.service.interfaces;

import com.company.aggregation.dto.PhotoDTO;
import com.company.aggregation.dto.student.StudentRequestDTO;
import com.company.aggregation.dto.student.StudentDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Student {
    StudentDto updateStudent(Integer id, StudentRequestDTO studentRequestDTO, HttpServletRequest httpServletRequest);

    void delete(Integer id,HttpServletRequest httpServletRequest);

    StudentDto getStudentById(Integer id, PhotoDTO photo,HttpServletRequest httpServletRequest);

    StudentDto create(StudentRequestDTO studentRequestDto, HttpServletRequest httpServletRequest);

    List<StudentDto> getList(Integer pageNo , Integer pageSize,HttpServletRequest httpServletRequest);

    void getList1();
}