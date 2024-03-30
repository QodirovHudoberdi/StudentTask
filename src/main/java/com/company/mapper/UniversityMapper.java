package com.company.mapper;

import com.company.aggregation.dto.UniversityDTO;
import com.company.aggregation.entity.UniversityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UniversityMapper extends EntityMapping<UniversityEntity, UniversityDTO,UniversityDTO>{
}
