package com.company.mapper;

import com.company.aggregation.dto.fieldstudy.FieldStudiesDto;
import com.company.aggregation.dto.fieldstudy.FieldStudiesRequestDTO;
import com.company.aggregation.entity.FieldStudiesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FieldStudyMapper extends EntityMapping<FieldStudiesEntity, FieldStudiesRequestDTO,FieldStudiesDto>{
}
