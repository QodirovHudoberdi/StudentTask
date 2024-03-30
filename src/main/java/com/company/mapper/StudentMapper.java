package com.company.mapper;

import com.company.aggregation.dto.student.StudentRequestDTO;
import com.company.aggregation.dto.student.StudentDto;
import com.company.aggregation.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapping<StudentEntity, StudentRequestDTO, StudentDto>{
}
