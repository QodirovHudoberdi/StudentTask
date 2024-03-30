package com.company.mapper;

import com.company.dto.student.StudentRequestDTO;
import com.company.dto.student.StudentDto;
import com.company.entity.StudentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapping<StudentEntity, StudentRequestDTO, StudentDto>{
}
