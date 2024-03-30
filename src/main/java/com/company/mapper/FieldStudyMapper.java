package com.company.mapper;

import com.company.dto.fieldstudy.FieldStudiesDto;
import com.company.dto.fieldstudy.FieldStudiesRequestDTO;
import com.company.entity.FieldStudiesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FieldStudyMapper extends EntityMapping<FieldStudiesEntity, FieldStudiesRequestDTO,FieldStudiesDto>{
}
